<%--
  Created by IntelliJ IDEA.
  User: dxb
  Date: 12/7/2016
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../2/include/_taglib_includes.jsp" %>
<html>
<head>
    <%@include file="../../../2/include/_html_head.jsp" %>
    <title>行政区管理_基于B/S程序的无纸化网络考试系统</title>

</head>
<body>
<%@include file="../../../2/include/_member_header.jsp" %>


<div class="container">
    <div class="content">


        <div class="row">
            <div class="col-md-12">
                <p><br/></p>
                <div class="row">

                    <%--省 start--%>
                    <div class="col-md-4">
                        <div class="row">
                            <div class="col-sm-2">
                                <h5>省</h5>
                            </div>
                            <div class="col-sm-10">
                                <div class="form-inline">
                                    <div class="form-group">
                                        <label class="sr-only">添加省名称</label>
                                        <input type="text" class="form-control"
                                               placeholder="添加省名称 回车添加"
                                               v-model="node.create.addProvince.valueStr"
                                               @keyup.enter="create(null,'addProvince')">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%--<h4>省--%>
                        <%--<a href="#" class="btn btn-default btn-xs pull-right" data-toggle="modal"--%>
                        <%--data-target="#create_Modal"--%>
                        <%--@click="createModal()">+</a>--%>
                        <%--</h4>--%>
                        <table class="table table-bordered" style="margin-top: 0.5rem">
                            <tbody>

                            <tr v-for="prov in province.list" :class="{'info':prov == province.active}">
                                <td style="cursor: hand" @click="setActive(prov)">
                                    {{prov.name}}
                                    <span class="pull-right">

                                    <%--<a href="#" data-toggle="modal" data-target="#create_Modal"--%>
                                       <%--@click="createModal(prov)">添加</a>--%>
                                    <a href="#" data-toggle="modal" data-target="#modifyModal"
                                       @click="modifyModal(prov)">修改</a>
                                    <a href="#" data-toggle="modal" data-target="#removeModal"
                                       @click="removeModal(prov)">移除</a>
                                    </span>
                                </td>
                            </tr>

                            <tr v-if="province.list.length<=0">
                                <td>
                                    暂无内容，请在表头添加
                                </td>
                            </tr>

                            </tbody>

                        </table>
                    </div>
                    <%--省 end--%>

                    <%--市 start--%>

                    <div class="col-md-4">
                        <%--<h4>市--%>
                            <%--<a href="#" class="btn btn-default  btn-xs pull-right" data-toggle="modal"--%>
                               <%--data-target="#create_Modal"--%>
                               <%--@click="createModal(province.active)" v-if="province.active">+</a>--%>
                        <%--</h4>--%>
                        <div class="row">
                            <div class="col-sm-2">
                                <h5>市</h5>
                            </div>
                            <div class="col-sm-10">
                                <div class="form-inline">
                                    <div class="form-group">
                                        <label class="sr-only">添加市名称</label>
                                        <input type="text" class="form-control"
                                               placeholder="添加市名称 回车添加"
                                               v-model="node.create.addCity.valueStr"
                                               v-if="province.active"
                                               @keyup.enter="create(province.active,'addCity')">
                                    </div>
                                </div>
                            </div>
                        </div>


                        <table class="table table-bordered" style="margin-top: 0.5rem">

                            <tbody>
                            <tr v-for="prov in city.list" :class="{'info':prov == city.active}">
                                <td style="cursor: hand" @click="setActive(prov)">
                                    {{prov.name}}
                                    <span class="pull-right">

                                    <%--<a href="#" data-toggle="modal" data-target="#create_Modal"--%>
                                       <%--@click="createModal(prov)">添加</a>--%>
 <a href="#" data-toggle="modal" data-target="#modifyModal"
    @click="modifyModal(prov)">修改</a>
                                    <a href="#" data-toggle="modal" data-target="#removeModal"
                                       @click="removeModal(prov)">移除</a>
                                    </span>

                                </td>
                            </tr>

                            <tr v-if="city.list.length<=0 && province.active">
                                <td>
                                    暂无内容，请在表头添加
                                </td>
                            </tr>
                            <tr v-if="!province.active">
                                <td>
                                    请先选择省级
                                </td>
                            </tr>

                            </tbody>

                        </table>
                    </div>
                    <%--市 end--%>

                    <%--区 start--%>
                    <div class="col-md-4">
                        <%--<h4>区县--%>
                            <%--<a class="btn btn-default  btn-xs pull-right" href="#" data-toggle="modal"--%>
                               <%--data-target="#create_Modal"--%>
                               <%--@click="createModal(city.active)" v-if="city.active">+</a></h4>--%>

                        <div class="row">
                            <div class="col-sm-3">
                                <h5 style="margin-left: -1rem ">区县</h5>
                            </div>
                            <div class="col-sm-9">
                                <div class="form-inline" style="margin-left: -1rem ">
                                    <div class="form-group">
                                        <label class="sr-only">添加区县名称</label>
                                        <input type="text" class="form-control"
                                               placeholder="添加区县名称 回车添加"
                                               v-model="node.create.addDistrict.valueStr"
                                               v-if="city.active"
                                               @keyup.enter="create(city.active,'addDistrict')">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <table class="table table-bordered" style="margin-bottom: 400px ;margin-top: 0.5rem">

                            <tbody>
                            <tr v-for="prov in district.list">
                                <td>
                                    {{prov.name}}
                                    <span class="pull-right">

                                    <a href="#" data-toggle="modal" data-target="#modifyModal"
                                       @click="modifyModal(prov)">修改</a>
                                    <a href="#" data-toggle="modal" data-target="#removeModal"
                                       @click="removeModal(prov)">移除</a>
                                    </span>
                                </td>
                            </tr>

                            <tr v-if="district.list.length<=0 && city.active">
                                <td>
                                    暂无内容，请在表头添加
                                </td>
                            </tr>
                            <tr v-if="!city.active">
                                <td>
                                    请先选择市级
                                </td>
                            </tr>

                            </tbody>

                        </table>
                    </div>
                    <%--区 end--%>

                </div>


                <p><br/></p>

            </div>
        </div>


        <%--modal--%>
        <div class="modal fade" id="modifyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document" v-if="node.modify">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title"
                            v-text="'修改['+node.modify.name+']'"></h4>
                    </div>
                    <div class="modal-body">

                        <div class="form-horizontal">
                            <div class="form-group">
                                <div class="col-md-12">
                                    <label class="text_title">名称：</label>
                                    <input type="text" class="form-control" v-model="node.modify.valueStr"
                                           @keyup.enter="modify()">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default btn-middle" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary btn-middle" data-dismiss="modal"
                                @click="modify()">保存修改
                        </button>

                    </div>
                </div>
            </div>
        </div>


        <div class="modal fade" id="removeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document" v-if="node.remove">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="detailModalLabel">删除确认</h4>
                    </div>
                    <div class="modal-body">
                        是否确定删除 [{{node.remove.name}}]？
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default btn-middle" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary btn-middle" @click="remove()"
                                data-dismiss="modal">确定
                        </button>
                    </div>
                </div>
            </div>
        </div>


        <div class="modal fade" id="create_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document" v-if="node.create.node">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="examInfoModalLabel" v-show="node.create.parent">添加
                            {{node.create.parent.name}} 的子级</h4>
                    </div>
                    <div class="modal-body">

                        <div class="form-horizontal">
                            <div class="form-group">
                                <div class="col-md-12">
                                    <label class="text_title">名称：</label>
                                    <input type="text" class="form-control" v-model="node.create.node.valueStr"
                                           @keyup.enter="create()">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default btn-middle" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary btn-middle" data-dismiss="modal"
                                @click="create()">添加
                        </button>

                    </div>
                </div>
            </div>
        </div>

        <%--modal end --%>


    </div>
</div>

<style>
    table tbody tr td a {
        display: none;
    }

    table tbody tr td:hover a {
        display: inline-block;
    }

    /*td{*/
    /*color: red;*/
    /*}*/
</style>

<%@include file="../../../2/include/_html_body.jsp" %>
<script src="${path}/media/2/js/organization/region2.js"></script>
</body>
</html>
