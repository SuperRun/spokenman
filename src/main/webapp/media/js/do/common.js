var common = {
    validate: {
        input: function (inputElement, len) {
            if (inputElement && inputElement.val().length >= len) {
                return true;
            }
            return false;
        }
    },
    alertMsg: function (boxId, msg, clazz) {
    	console.log(boxId+':'+msg);
        var msgBox = $('#' + boxId);
        if (!msgBox.is(":hidden")) {
            msgBox.hide(100);
        }
        msgBox.attr('class', clazz);
        msgBox.html(msg);
        msgBox.show(200);
    },
    alertMsgHide: function (boxId) {
    	console.log(boxId+'->hide');
        $('#' + boxId).hide(100);
    }
};