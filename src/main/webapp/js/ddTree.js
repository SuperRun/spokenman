var undo_steps = new Array();		// 撤销的堆
var redo_steps = new Array();		// 重做的堆
var tree;
String.prototype.getRequestParameter = function (key) { 
	var re = new RegExp(key + '=([^&]*)(?:&)?'); 
	return this.match(re) && this.match(re)[1]; 
}; 
$(document).ready(function() {
	 // --- Initialize first Dynatree -------------------------------------------
	var id=location.search.getRequestParameter('id');
	var withMainItem=location.search.getRequestParameter('withMainItem');
	if(id=="null")
		return;
	tree=$("#tree");
	tree.dynatree({
	  keyboard: false,
	  imagePath: 'dynatree_images/',
	  initAjax: {
		url: "getDDTreeById.json?id="+id+"&withMainItem="+withMainItem
	  },
	  onClick: function(node, event) {
		parent.showContent(node);		
	  },
	  onBlur: function(node) {
		console.info('666');
		return false;
	  },
	  onExpand: function(flag, node) {
		window.parent.reBindMousedown();
	  }	  
	});
	
	tree = tree.dynatree("getTree");

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
		//var node = tree.dynatree("getActiveNode");
		var node = tree.activeNode;
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
		//var node = tree.dynatree("getActiveNode");
		var node = tree.activeNode;
		var undo_step = {};
		undo_step.type = 'remove';
		undo_step.node = node;
		undo_step.nodeParent = node.getParent();
		undo_steps.push(undo_step);
		node.remove();
		if(parent.removeAuth)
			parent.removeAuth(node.data.key);
	}

	/**
	* 描述: 上一个节点
	*/
	prevNode = function() {
		//var node = tree.dynatree("getActiveNode");
		var node = tree.activeNode;
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
		//var node = tree.dynatree("getActiveNode");
		var node = tree.activeNode;
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
	* 获得当前节点
	* add by czg
	* since 2014-8-1
	*/
	selectedNode = function() {
		//var node = tree.dynatree("getActiveNode");
		return tree.activeNode;		
	}
	getNodeByKey=function(key){
		return tree.getNodeByKey(key);
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
		var addOrEdit="add";
		element = $(element).find('a')[0];
		var oldhtml = element.innerHTML;
		var newobj = document.createElement('input');//创建新的input元素
		$(newobj).attr('placeholder', '请输入名称');
		$(newobj).addClass('col-md-12');
		$(newobj).css("margin-bottom", "0");
		newobj.type = 'text';//为新增元素添加类型
		/*
		为input添加回车响应
		by：czg  2014-8-1
		*/
		$(newobj).keypress(function (e) {
            if (e.keyCode == 13) {
				newobj.blur();
                //alert(newobj.value);
				//element.innerHTML = this.value ? this.value : oldhtml;
				//当触发时判定新增元素值是否为空，为空则不修改，并返回原有值 
				//node.data.title = element.innerHTML;
				//node.render();
            }
        });
		// 关键字段要默认载入
		if(oldhtml != "未命名的组织结构") {
			newobj.value = oldhtml;
			addOrEdit="edit";
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
			if(addOrEdit=="add"&&parent.addNode)
				parent.addNode(node);
			if(addOrEdit=="edit"&&parent.editNode)
				parent.editNode(node);
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
		tree.dynatree("option", "initAjax", {url:newUrl});
		tree.reload();
		undo_steps = new Array();		// 撤销的堆
		redo_steps = new Array();		// 重做的堆
	}
	reloadTree=function(){
		tree.reload();
	}
});