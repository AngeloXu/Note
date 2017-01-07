//初始化
var SUCCESS=0;
var model={};//当前页面中的数据模型
$(function(){
	//页面加载以后，立即加载笔记本列表
	loadNotebooksAction();//edit_notebook.js
	//绑定点击笔记本列表的事件
	/*$('#notebooks').click(function(){
		console.log('Click notebooks');
	});*/
	/*$('#notebooks').on('click','li',function(){
		console.log('Click notebooks');
		console.log(this);
	});*/
	//showNotesAction函数定义在edit_note.js中
	$('#notebooks').on('click','li.notebook',showNotesAction);
	$('#notebooks').on('click','li.more',loadNotebooksAction);
	//绑定笔记列表点击事件
	//loadNoteAction方法在edit_note.js中定义
	$('#notes').on('click','li',loadNoteAction);
	$('#save_note').click(saveNoteAction);
	//添加笔记按钮事件
	$('#add_note').click(showAddNoteDialog);
	$('#can').on('click','.close,.cancle',closeDialog);
	$('#notes').on('click','.btn_slide_down',showNoteMenu);
	$('#add_notebook').click(showCreateNotebookAction);
});

function showNoteMenu(){
	console.log('showNoteMenu');
	$('#notes .note_menu').hide();
	$(this).parent().next().toggle();
	
	
}

function closeDialog(){
	$('#can').empty();
	$('.opacity_bg').hide();
}