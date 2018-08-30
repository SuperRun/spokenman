function ddReqMap(id) {
    var p = fns.ajax.path("/organization/region/dd");
    return id ? p + "?id=" + id : p;
}

var v = new Vue({
    el: '.content',
    data: {
        province: {
            list: [],
            active: null
        },
        city: {
            list: [],
            active: null
        },
        district: {
            list: [],
            active: null
        },
        node: {
            modify: null,
            remove: null,
            create: {
                parent: null,
                node: null,
                addProvince: {
                    valueStr: ''
                },
                addCity: {
                    valueStr: ''
                },
                addDistrict: {
                    valueStr: ''
                }
            }
        }

    },

    watch: {
        'province.active': function (val) {
            if (val) {

                console.log("省 切换到 " + val);
                $.get(ddReqMap(val.id), function (r) {
                    v.city.list = r.data;
                    v.city.active = null;
                    v.district.list = [];

                });
            }
        },
        'city.active': function (val) {

            if (val) {

                console.log("市 切换到 " + val);
                $.get(ddReqMap(val.id), function (r) {
                    v.district.list = r.data;
                });
            }
        }
    },

    methods: {
        setActive: function (val) {
            var i;
            for (i = 0; i < this.province.list.length; i++) {
                var pro = this.province.list[i];
                if (pro == val) {
                    this.province.active = val;
                    return;
                }
            }

            for (i = 0; i < this.city.list.length; i++) {
                var city = this.city.list[i];
                if (city == val) {
                    this.city.active = val;
                    return;
                }
            }

            for (i = 0; i < this.district.list.length; i++) {
                var district = this.district.list[i];
                if (district == val) {
                    this.district.active = val;
                    return;
                }
            }


        },
        modifyModal: function (val) {
            val['valueStr'] = val.name;
            this.node.modify = val;
        },
        modify: function () {
            var n = this.node.modify;
            $.post(fns.ajax.path('/mdd/' + n.id + "/modify"), n, function (r) {
                //更新被修改节点
                $('#modifyModal').modal('hide');

                for (i = 0; i < v.province.list.length; i++) {
                    var pro = v.province.list[i];
                    if (pro.id == n.id) {
                        v.province.list[i].name = n.valueStr;
                        return;
                    }
                }

                for (i = 0; i < v.city.list.length; i++) {
                    var city = v.city.list[i];
                    if (city.id == n.id) {
                        v.city.list[i].name = n.valueStr;
                        return;
                    }
                }

                for (i = 0; i < v.district.list.length; i++) {
                    var district = v.district.list[i];
                    if (district.id == n.id) {
                        v.district.list[i].name = n.valueStr;
                        return;
                    }
                }

            });
        },
        removeModal: function (val) {
            this.node.remove = val;
        },

        remove: function () {
            $.get(fns.ajax.path("/mdd/" + this.node.remove.id + "/delete"), function (r) {
                var n = v.node.remove;
                for (i = 0; i < v.province.list.length; i++) {
                    var pro = v.province.list[i];
                    if (pro.id == n.id) {
                        v.province.list.splice(i, 1);
                        return;
                    }
                }

                for (i = 0; i < v.city.list.length; i++) {
                    var city = v.city.list[i];
                    if (city.id == n.id) {
                        v.city.list.splice(i, 1);
                        return;
                    }
                }

                for (i = 0; i < v.district.list.length; i++) {
                    var district = v.district.list[i];
                    if (district.id == n.id) {
                        v.district.list.splice(i, 1);
                        return;
                    }
                }
            });
        },

        createModal: function (val) {
            this.node.create.parent = val;
            this.node.create.node = {
                parentId: val && val.id ? val.id : null,
                valueStr: ''
            };

        },
        create: function (parentNode, type) {
            var n = this.node.create.node;

            // 表头创建node
            if (type) {
                n = this.node.create[type];
            }
            if (parentNode) {
                n.parentId = parentNode.id;
            }


            $.post(fns.ajax.path("/organization/region"), n, function (r) {
                if (r.success) {
                    var newNode = {
                        id: r.data,
                        name: n.valueStr
                    };
                    $('#create_Modal').modal('hide');
                    n.valueStr = '';
                    if (!n.parentId) {
                        // 省
                        v.province.list.push(newNode);
                        return;
                    }

                    for (i = 0; i < v.province.list.length; i++) {
                        var pro = v.province.list[i];
                        if (pro.id == n.parentId) {
                            v.city.list.push(newNode);
                            return;
                        }
                    }

                    for (i = 0; i < v.city.list.length; i++) {
                        var city = v.city.list[i];
                        if (city.id == n.parentId) {
                            v.district.list.push(newNode);
                            return;
                        }
                    }


                }
            })

        }

    }


});

$().ready(function () {

    //初始化数据
    $.get(ddReqMap(), function (r) {
        v.province.list = r.data;
    });

});


