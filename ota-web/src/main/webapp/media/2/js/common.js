/**
 * 必要的js 指定了项目名称等
 * Created by dxb on 12/7/2016.
 */

var htmlHead = new Vue({
    el: 'head',
    data: {
        project: {
            name: '基于B/S程序的无纸化网络考试系统'
        }
    }
});
var htmlFooter = new Vue({
    el: '.footer',
    data: {
        footer: {
            cp: 'Copyright：2016-2017第一学期 J2EE课程设计'
        }
    }
});

var fns = {
    ajax: {
        error: function (r) {
            var info = '未知错误';
            if (r) {
                if (r.error) {
                    info = r.error;
                } else if (r.status) {
                    info = '连接错误 (' + r.status + " " + r.statusText + ")";
                } else if ('error' == r.statusText) {
                    info = '连接错误 请检查网络连接';
                } else {
                    console.error('未知错误', r);
                }
            }
            console.error(r);
            console.error(info);
            alert(info);
        },
        path: function (reqMapping) {
            return path + reqMapping;
        }
    }
};


$.ajaxSetup({
    error: function (r) {
        fns.ajax.error(r);
    }
});