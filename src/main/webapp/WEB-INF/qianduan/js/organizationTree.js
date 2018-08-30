/**
 * Created by YvonneQ on 2016/11/17.
 */
var undo_steps = new Array();		// 撤销的堆
var redo_steps = new Array();		// 重做的堆
var tree;

$(document).ready(function() {
    // --- Initialize first Dynatree -------------------------------------------
    $("#tree").dynatree({
        keyboard: false,
        imagePath: 'images/',
        initAjax: {
            url: "tree-data.json"
        },
        onLazyRead: function(node){
            // Mockup a slow reqeuest ...
            node.appendAjax({
                url: "sample-data2.json"
                // debugLazyDelay: 750 // don't do this in production code
            });
        },
        onDblClick: function(node, event) {
            showElement(node.span, node);
        },
        onClick: function(node, event) {
            console.info('555');
        },
        onBlur: function(node) {
            console.info('666');
            return false;
        },
        onExpand: function(flag, node) {
            window.parent.reBindMousedown();
        },
        dnd: {
            onDragStart: function(node) {
                /** This function MUST be defined to enable dragging for the tree.
                 *  Return false to cancel dragging of node.
                 */
                // logMsg("tree.onDragStart(%o)", node);
                return true;
            },
            onDragStop: function(node) {
                // This function is optional.
                // logMsg("tree.onDragStop(%o)", node);
            },
            autoExpandMS: 1000,
            preventVoidMoves: true, // Prevent dropping nodes 'before self', etc.
            onDragEnter: function(targetNode, sourceNode) {
                /** sourceNode may be null for non-dynatree droppables.
                 *  Return false to disallow dropping on node. In this case
                 *  onDragOver and onDragLeave are not called.
                 *  Return 'over', 'before, or 'after' to force a hitMode.
                 *  Return ['before', 'after'] to restrict available hitModes.
                 *  Any other return value will calc the hitMode from the cursor position.
                 */
                // logMsg("tree.onDragEnter(%o, %o)", node, sourceNode);
                // var undo_step = {};
                // undo_step.type = 'drag';
                // undo_step.node = childNode;
                // undo_steps.push(undo_step);
                return true;
            },
            onDragOver: function(node, sourceNode, hitMode) {
                /** Return false to disallow dropping this node.
                 *
                 */
                // logMsg("tree.onDragOver(%o, %o, %o)", node, sourceNode, hitMode);
                // Prevent dropping a parent below it's own child
                if(node.isDescendantOf(sourceNode)){
                    return false;
                }
                // Prohibit creating childs in non-folders (only sorting allowed)
                if( !node.data.isFolder && hitMode === "over" ){
                    return "after";
                }
            },
            onDrop: function(node, sourceNode, hitMode, ui, draggable) {
                /** This function MUST be defined to enable dropping of items on
                 * the tree.
                 */
                // logMsg("tree.onDrop(%o, %o, %s)", node, sourceNode, hitMode);
                $('#undoButton', parent.document).removeClass('disabled');
                var undo_step = {};
                undo_step.type = 'drop';
                undo_step.sourceNode = sourceNode;
                undo_step.hitMode = hitMode;
                undo_step.sourceNodeParent = sourceNode.getParent();
                undo_step.targetNode = node;
                undo_steps.push(undo_step);
                sourceNode.move(node, hitMode);
                // expand the drop target
                //        sourceNode.expand(true);
            },
            onDragLeave: function(node, sourceNode) {
                /** Always called if onDragEnter was called.
                 */
                // logMsg("tree.onDragLeave(%o, %o)", node, sourceNode);
            },
            strings: {
                loading: "数据正在记载中...",
                loadError: "数据加载错误!"
            },
            classNames: {
            }
        }
    });

    tree = $("#tree").dynatree("getTree");

    // tree[0].removeEventListener('focus', __focusHandler, false);
    // tree[0].removeEventListener('blur', __focusHandler, false);

    function __focusHandler(event) {
        // Handles blur and focus.
        // Fix event for IE:
        // doesn't pass JSLint:
//			event = arguments[0] = $.event.fix( event || window.event );
        // what jQuery does:
//			var args = jQuery.makeArray( arguments );
//			event = args[0] = jQuery.event.fix( event || window.event );
        event = $.event.fix( event || window.event );
        var dtnode = $.ui.dynatree.getNode(event.target);
        return dtnode ? dtnode._onFocus(event) : false;
    }

    /**
     * 描述: 添加节点
     */
    addNode = function() {
        $('#undoButton', parent.document).removeClass('disabled');
        var node = $("#tree").dynatree("getActiveNode");
        node.expand(true);
        var childNode = node.addChild({
            title: "未命名的组织结构"
        });
        showElement(childNode.span, childNode);
        var undo_step = {};
        undo_step.type = 'add';
        undo_step.node = childNode;
        undo_steps.push(undo_step);
    }

    /**
     * 描述: 删除节点
     */
    removeNode = function() {
        $('#undoButton', parent.document).removeClass('disabled');
        var node = $("#tree").dynatree("getActiveNode");
        var undo_step = {};
        undo_step.type = 'remove';
        undo_step.node = node;
        undo_step.nodeParent = node.getParent();
        undo_steps.push(undo_step);
        node.remove();
    }

    /**
     * 描述: 上一个节点
     */
    prevNode = function() {
        var node = $("#tree").dynatree("getActiveNode");
        var preNode = node.getPrevSibling();
        if(preNode == undefined) {
            preNode = node.getParent();
        } else {
            preNode = getNodeLast(preNode);
        }
        preNode.activate();
    }

    /**
     * 描述: 下一个节点
     */
    nextNode = function() {
        var node = $("#tree").dynatree("getActiveNode");
        var nextNode;
        if(node.hasChildren() === true) {
            nextNode = node.childList[0];
        } else {
            var nextNode = node.getNextSibling();
            if(nextNode == undefined) {
                nextNode = getParentNext(node);
            }
        }
        nextNode.activate();
    }

    /**
     * 描述: 撤销操作
     */
    undo = function() {
        if(!$('#undoButton', parent.document).hasClass('disabled')) {
            var undo_step = undo_steps.pop();
            var type = undo_step.type;
            if(type != undefined) {
                if(type == 'add') {
                    var redo_step = {};
                    redo_step.type = 'remove';
                    redo_step.node = undo_step.node;
                    redo_step.nodeParent = undo_step.node.getParent();
                    redo_steps.push(redo_step);
                    $('#redoButton', parent.document).removeClass('disabled');
                    undo_step.node.remove();
                } else if(type == 'remove') {
                    var childNode = undo_step.nodeParent.addChild(undo_step.node.data);
                    var redo_step = {};
                    redo_step.type = 'add';
                    redo_step.node = childNode;
                    redo_steps.push(redo_step);
                    $('#redoButton', parent.document).removeClass('disabled');
                } else if(type == 'edit') {
                    var node = undo_step.node;
                    var originalTitle = node.data.title;
                    node.data.title = undo_step.originalTitle;
                    node.render();
                    var redo_step = {};
                    redo_step.type = 'edit';
                    redo_step.node = node;
                    redo_step.originalTitle = originalTitle;
                    redo_steps.push(redo_step);
                    $('#redoButton', parent.document).removeClass('disabled');
                } else if(type == 'drop') {
                    var hitMode = undo_step.hitMode;
                    var sourceNode = undo_step.sourceNode;
                    var sourceNodeParent = undo_step.sourceNodeParent;
                    var targetNode = undo_step.targetNode;
                    if(hitMode == 'over' || hitMode == 'child') {
                        sourceNode.move(sourceNodeParent, hitMode);
                        var redo_step = {};
                        redo_step.type = 'drop';
                        redo_step.sourceNode = sourceNode;
                        redo_step.hitMode = hitMode;
                        redo_step.sourceNodeParent = targetNode;
                        redo_step.targetNode = sourceNodeParent;
                        redo_steps.push(redo_step);
                    } else if(hitMode == 'before' || hitMode == 'after') {
                        if(targetNode.getParent() != sourceNodeParent) {
                            var curHitMode = 'child';
                            sourceNode.move(sourceNodeParent, curHitMode);
                            var redo_step = {};
                            redo_step.type = 'drop';
                            redo_step.sourceNode = sourceNode;
                            redo_step.hitMode = curHitMode;
                            redo_step.sourceNodeParent = targetNode.getParent();
                            redo_step.targetNode = sourceNodeParent;
                            redo_steps.push(redo_step);
                        } else {
                            var curHitMode;
                            if(hitMode == 'before') {
                                curHitMode = 'after';
                            } else {
                                curHitMode = 'before';
                            }
                            sourceNode.move(targetNode, curHitMode);
                            var redo_step = {};
                            redo_step.type = 'drop';
                            redo_step.sourceNode = sourceNode;
                            redo_step.hitMode = curHitMode;
                            redo_step.sourceNodeParent = sourceNodeParent;
                            redo_step.targetNode = targetNode;
                            redo_steps.push(redo_step);
                        }
                    }
                    $('#redoButton', parent.document).removeClass('disabled');
                }
            }
            if(undo_steps.length == 0) {
                $('#undoButton', parent.document).addClass('disabled');
            }
        }
    }

    /**
     * 描述: 重做操作
     */
    redo = function() {
        if(!$('#redoButton', parent.document).hasClass('disabled')) {
            var redo_step = redo_steps.pop();
            var type = redo_step.type;
            if(type != undefined) {
                if(type == 'add') {
                    var undo_step = {};
                    undo_step.type = 'remove';
                    undo_step.node = redo_step.node;
                    undo_step.nodeParent = redo_step.node.getParent();
                    undo_steps.push(undo_step);
                    $('#undoButton', parent.document).removeClass('disabled');
                    redo_step.node.remove();
                } else if(type == 'remove') {
                    var childNode = redo_step.nodeParent.addChild(redo_step.node.data);
                    var undo_step = {};
                    undo_step.type = 'add';
                    undo_step.node = childNode;
                    undo_steps.push(undo_step);
                    $('#undoButton', parent.document).removeClass('disabled');
                } else if(type == 'edit') {
                    var node = redo_step.node;
                    var originalTitle = node.data.title;
                    node.data.title = redo_step.originalTitle;
                    node.render();
                    var undo_step = {};
                    undo_step.type = 'edit';
                    undo_step.node = node;
                    undo_step.originalTitle = originalTitle;
                    undo_steps.push(undo_step);
                    $('#undoButton', parent.document).removeClass('disabled');
                } else if(type == 'drop') {
                    var sourceNode = redo_step.sourceNode;
                    var targetNode = redo_step.sourceNodeParent;
                    var hitMode = redo_step.hitMode;
                    sourceNode.move(targetNode, hitMode);
                    var undo_step = {};
                    undo_step.type = 'drop';
                    undo_step.sourceNode = redo_step.sourceNode;
                    undo_step.hitMode = redo_step.hitMode;
                    undo_step.sourceNodeParent = redo_step.targetNode;
                    undo_step.targetNode = redo_step.sourceNodeParent;
                    undo_steps.push(undo_step);
                    $('#undoButton', parent.document).removeClass('disabled');
                }
            }
            if(redo_steps.length == 0) {
                $('#redoButton', parent.document).addClass('disabled');
            }
        }
    }

    /**
     * 描述: 双击编辑文字
     */
    showElement = function(element, node) {
        element = $(element).find('a')[0];
        var oldhtml = element.innerHTML;
        var newobj = document.createElement('input');//创建新的input元素
        $(newobj).attr('placeholder', '请输入名称');
        $(newobj).addClass('span12');
        $(newobj).css("margin-bottom", "0");
        newobj.type = 'text';//为新增元素添加类型
        // 关键字段要默认载入
        if(oldhtml != "未命名的组织结构") {
            newobj.value = oldhtml;
        }
        $(newobj).die().live('blur',function() {
            $('#undoButton', parent.document).removeClass('disabled');
            var undo_step = {};
            undo_step.type = 'edit';
            undo_step.node = node;
            undo_step.originalTitle = oldhtml;
            undo_steps.push(undo_step);
            element.innerHTML = this.value ? this.value : oldhtml;//当触发时判定新增元素值是否为空，为空则不修改，并返回原有值
            node.data.title = element.innerHTML;
            node.render();
        });
        element.innerHTML = '';
        element.appendChild(newobj);

        newobj.focus();
        newobj.select();
    }

    /**
     * 描述: 获取节点上级的下一个非空节点
     */
    getParentNext = function(node) {
        var parNode = node.getParent();
        var parNextNode = parNode.getNextSibling();
        if(parNextNode != undefined) {
            return parNextNode;
        } else {
            return getParentNext(parNode);
        }
    }

    /**
     * 描述: 获取某节点的最后一个非空节点
     */
    getNodeLast = function(node) {
        var childNodes = node.getChildren();
        if(childNodes != null) {
            var lastChild = childNodes[childNodes.length - 1];
            return getNodeLast(lastChild);
        } else {
            return node;
        }
    }

    /**
     * 描述: 改变树的url
     */
    changeUrl = function(newUrl) {
        console.info(newUrl);
        $("#tree").dynatree("option", "initAjax", { url: newUrl });
        tree.reload();
        undo_steps = new Array();		// 撤销的堆
        redo_steps = new Array();		// 重做的堆
    }
});