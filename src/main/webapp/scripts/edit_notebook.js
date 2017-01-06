//与笔记有关的代码
/*
 * 封转与笔记本有关的操作方法
*/

function loadNotebooksAction(){
	//获取page数据，//$(this)是li
	var page=$(this).data('page');
	if(!page){
		page=0;
	}
	//console.log("loadNotebooksAction()");
	// var url='notebook/list.do';
	var url='notebook/notebooks.do';
	var data={'userId':getCookie('userId'),page:page};
	$.getJSON(url,data,function(result){
		if(result.state==SUCCESS){
			var list=result.data;
			model.updateNotebooks(list,page);
		}
	});
}

/*
 * <li class="online"> <a class='checked'> <i class="fa fa-book" title="online"
 * rel="tooltip-bottom"> </i> 默认笔记本</a></li>
 */
model.updateNotebooks=function(list,page){
	var template='<li class="online notebook">'+
					'<a>'+
						'<i class="fa fa-book" title="online" ></i>'+
							'[notebook.name]</a></li>';
	if(!this.notebooks){
		this.notebooks=list;
	}else{
		this.notebooks=this.notebooks.concat(list);
	}
	
	//console.log(this);
	var ul=$("#notebooks").empty();
	for(var i=0;i<this.notebooks.length;i++){
		var notebook=this.notebooks[i];
		// id name
		var li=template.replace('notebook.name',notebook.name);
		// 在DOM对象上绑定数据index
		var li=$(li).data('index',i);
		ul.append(li);
	}
	var li=$('<li class="online more"><a>More...</a></li>');
	li.data('page',page+1);
	ul.append(li);
};