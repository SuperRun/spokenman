new Vue({
  el: "#app", mounted: function() {
    var self = this;
    self.fetch();
  }, methods: {
    fetch: function() {
      var self = this;
      var userType = $('#type').val();
      var userStatus = $('#status').val();
      console.log("type=" + userType);
      console.log("status=" + userStatus);
      if (userType == 0) {
        layui.use('layer', function() {
          var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
          parent.layer.close(index);//关闭弹出的子页面窗口
          console.log("type1" + userType);
          //平台管理员
          parent.window.location.href = "/index";
        });
      } else if (userType == 1) {
        if (userStatus != 1) {//跳转到提示页面
          layui.use('layer', function() {
            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
            parent.layer.close(index);//关闭弹出的子页面窗口
            console.log("type" + userType);
            //普通用户
            parent.window.location.href = "/perUser/applyTip";
          });
        } else {
          layui.use('layer', function() {
            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
            parent.layer.close(index);//关闭弹出的子页面窗口
            console.log("type" + userType);
            //普通用户
            parent.window.location.href = "/perUser/resIndex";
          });
        }

      } else if (userType == 2) {
        if (userStatus != 1) {//跳转到提示页面
          layui.use('layer', function() {
            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
            parent.layer.close(index);//关闭弹出的子页面窗口
            console.log("type" + userType);
            //普通用户
            parent.window.location.href = "/perUser/applyTip";
          });
        } else {
          layui.use('layer', function() {
            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
            parent.layer.close(index);//关闭弹出的子页面窗口
            console.log("type2" + userType);
            //单位用户
            parent.window.location.href = "/perUser/resIndex";
          });
        }

      }

    }
  }

});