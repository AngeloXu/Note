//封装全部与笔记有关的脚本

function showNotesAction(){	
	//console.log(this);//this是li元素
	//获取选中元素的序号，此序号是在显示笔记本列表时候绑定的
	var li=$(this);
	var index=li.data('index')
	//处理视觉效果
	li.parent().find('a').removeClass('checked');
	li.find('a').addClass('checked');
	//让模型显示当前笔记本的全部笔记
	model.showNotes(index);
	
}
//显示选项笔记本的全部笔记
model.showNotes=function(notebookIndex){
	//找到当前笔记本信息
	console.log(notebookIndex);
	this.notebookIndex=notebookIndex;//保存选定的笔记本序号到model
	console.log(this.notebookIndex);
	var notebook=this.notebooks[notebookIndex];
	var url='note/list.do';
	var data={'notebookId':notebook.id}
	//向服务器发起Ajax请求获取笔记列表
	$.getJSON(url,data,function(result){
		if(result.state==SUCCESS){
			//console.log(result.data);
			//更新笔记列表
			model.updateNotes(result.data);
			
		}else{
			alert(result.message);
		}
	});
}
//将notes显示到界面
model.updateNotes=function(notes){
	//console.log(notes);
	if(notes){
		this.notes=notes;
	}
	var template=
'<li class="online"><a>'
		+'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>[note.title]<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>'
	+'</a><div class="note_menu" tabindex="-1">'
	 +'<dl>'
		+'<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'
		+'<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'
		+'<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'
	 +'</dl>'
	+'</div>'
+'</li>';
	var ul=$('#notes').empty();
	for(var i=0;i<this.notes.length;i++){
		var note=this.notes[i];
		var li=template.replace('[note.title]',note.title);
		li=$(li).data('index',i);
		if(i==this.noteIndex){
			li.children('a').addClass('checked');
		}
		ul.append(li);
	}
	ul.find('a').removeClass('checked');
	
}
//处理笔记列表点击事件
function loadNoteAction(){
	var li=$(this);//当前对象
	//处理显示效果
	li.parent().find('a').removeClass('checked');
	li.find('a').addClass('checked');
	//获取选的元素的序号
	var index=li.data('index');
	model.loadNote(index);
}
model.loadNote=function(index){
	//在model中记录当前选定笔记的位置，在更新title时候利用这个序号找到笔记的位置
	
	this.noteIndex=index;
	var url="note/load.do";
	var	data={id:model.notes[index].id};
	$.getJSON(url,data,function(result){
		if(result.state==SUCCESS){
			model.updateNote(result.data);
			
		}else{
			alert(result.message);
		}
	});
}

//将note对象显示到编辑区域
model.updateNote=function(note,newNote){
	this.note=note;
	$('#input_note_title').val(this.note.title);
	um.setContent(this.note.body);
	if(newNote){
		this.noteIndex=0;
		this.notes.unshift({id:note.id,title:note.title});
		this.updateNotes();
	}
};
//处理保存按钮事件
function saveNoteAction(){
	console.log('saveNoteAction');
	var title=$('#input_note_title').val();
	var body=um.getContent();
	var id=model.note.id;
	if(title.replace(" ","")==""){
		title=model.note.title;
		$('#input_note_title').val(title);
	}
	if(title==model.note.title&&body==model.note.body){
		return ;
	}
	var url='note/save.do';
	var data={id:id,title:title,body:body};
	console.log(data);
	$.post(url,data,function(result){
		if(result.state==SUCCESS){
			if(result.data==false){
				alert('更新失败');
				return;
			}
			console.log('成功');
			//更新标题
			model.updateTitle(title);
			
			
		}else{
			alert(result.message);
		}
	});
}
model.updateTitle=function(title){
	//找到当前选定笔记的序号
	var index=model.noteIndex;
	console.log(index);
	this.notes[index].title=title;
	//更新笔记列表的显示
	this.updateNotes();
	
}
//响应添加笔记按钮事件，打开添加笔记对话框
function showAddNoteDialog(){
	if(model.notebookIndex==null){
		alert('请先选择笔记本！');
		return;
	}
	$('#can').load('alert/alert_note.jsp',function(){
		//页面加载以后执行
		//console.log("加载以后执行");
		$('#can .sure').click(addNoteAction);
		
	});
	$('.opacity_bg').show();	
}
//处理点击保存笔记按钮事件
function addNoteAction(){
	var url='note/add.do';
	var notebookId=model.notebooks[model.notebookIndex].id;
	var title=$('#can #input_note').val();
	if(title.replace(' ','')==''){
		return;
	}
	var data={userId:getCookie('userId'),notebookId:notebookId,title:title};
	$.post(url,data,function(result){
		if(result.state==SUCCESS){
			var note=result.data;
			model.updateNote(note,true);
			closeDialog();
		}else{
			alert(result.message);
		}
	});
	
}
