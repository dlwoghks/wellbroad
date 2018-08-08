


    function touritem(icon,code,name,description){
		this.icon =icon;
		this.code =code;
		this.name =name;
		this.description = description;
	}
	
	var tourlist = new Array();
	var isSecond = false;
	var targetDIV; //.tour-icon-list
	var iconIndex = 0;
	var base = "${pageContext.request.contextPath}";
	
	
	var planeEvent = function () {
        var index = $('#tour option:selected').val();
        if(index == '선택') return;
        var item = tourlist[index];
        targetDIV = saveTarget($(this));//.plane
        var innerText = '<h2>'+item.name+'</h2>' + '<div style="margin:90px;"><p>'+item.description+'</p></div>';
        
        for(i=0; i<2; i++){
        		innerText += '<img src="${pageContext.request.contextPath}/view/images/tour/'+item.code+'_'+i+'.jpg" width="800px" height="700px"/><hr>'
  		}
        
        $(".modal-body #modal-val").html(innerText);
    };
	
	// return tour-icon-list
	function saveTarget(target){
		return target.parent('div').parent('div');  //target(.plane)의 부모 add-icon의 부모 tour-icon-list
	}
	
	
	$(document).on("click", ".plane", planeEvent); //비행기 클릭시 발생하는 이벤트 
	/* 팝업창에 내용넣기  */ 
   $(".plane").click(planeEvent);
    
    
	
	
	function addDay(dayid){
		
		var dItem = 
		'<div class="day-item" id="day'+dayid+'">' +
 		'<h3 class="day-count">'+dayid+'일차</h3>' +   
		'<div class ="icon-group">'+
		   '<div class="tour-icon-list">'+
			   '<div class="add-icon">'+
				  	 '<button type="button" class="btn plane" data-toggle="modal" data-target="#my80sizeCenterModal" >'+
			             '<img src="'+base+'/view/icon/outline3.png">'+	
			   		 '</button>'+/* 
						'<img src="${pageContext.request.contextPath}/view/icon/airport.png" width="155px" height="155px" />'+ */
					    '<img class="arrowb" src="${pageContext.request.contextPath}/view/icon/arrow.png"/>'+
			  '</div>'+
			'</div>'+
		'</div>';
		
		/* var dItem = day.clone(); */
		
		
		$('.day-group').append(dItem);
/* 		dItem.attr('id','day'+ dayid);
		dItem.find('.day-count').text(dayid + '일차');
		dItem.find('.plane').click(planeEvent); */
		
	}
	
	function removeDay(){
		$('.day-item').last().remove();
	}
	
	

  $(function(){   
	  
/* 달력  */
	 $('#datepicker1').datepicker({ //startdate
	  format: "yyyy-mm-dd",
	  startView: 1,
	  todayBtn:"linked",
	  language: "kr",
	  orientation: "top auto",
	  keyboardNavigation: false,
	  forceParse: false,
	  autoclose: true,
	  todayHighlight: true
      });
       
	 $('#datepicker2').datepicker({ //enddate
		  format: "yyyy-mm-dd",
		  startView: 1,
		  todayBtn:"linked",
		  language: "kr",
		  orientation: "top auto",
		  keyboardNavigation: false,
		  forceParse: false,
		  autoclose: true,
		  todayHighlight: true
	      });
	       
       $('#datepicker1').change(function(){
    	  	
    	   if(isSecond){ //일까지 선택했을때 
   	   		var startDate = new Date($('#datepicker1').val());
   	   		var endDate = new Date($('#datepicker2').val());
   	   		
   	   		var ONE_DAY = 1000 * 60 * 60 * 24;

   	   	    // Convert both dates to milliseconds
   	   	    var date1_ms = startDate.getTime()
   	   	    var date2_ms = endDate.getTime()

   	   	    // Calculate the difference in milliseconds
   	   	    var difference_ms = Math.abs(date1_ms - date2_ms)

   	   	    // 시작날짜 마지막날짜 차이
   	   	    var days = Math.round(difference_ms/ONE_DAY);
   	   		
   	   		if(date1_ms > date2_ms){
   	   			alert('여행 마지막일이 시작일 보다 빠를 수 없습니다.');
   	   			$('#datepicker1').val('');
   	   			$('#datepicker2').val('');
   	   			isSecond = false;
   	   			return;
   	   		}
   	   		
   	   		if(days > 10){
   	   			alert('10일을 넘길 수 없습니다.');
   	   			$('#datepicker1').val('');
   	   			$('#datepicker2').val('');
   	   			isSecond = false;
   	   			return;
   	   		}
			
   	   			 			 				   //<div class="day-item" id="day1">
   	   		var dayChildSize = $('.day-group').find($('.day-item')).length;
   	   		var inc = 0;
   	   							
   	   		if(dayChildSize > (days+1)){
   	   			var dif = dayChildSize - (days+1) ;
   	   		
				for(i=0; i< dif; i++){    	   			
	    	   			removeDay();	    	   			
	    	   		}
			
   	   		} else if(dayChildSize < (days+1)){	//이미 추가되어있는 1일차 이후에 추가 
				var dif = (days+1) - dayChildSize;
   	   			             
   	   			inc = dayChildSize + 1 ;
	    	   		for(i=0; i<dif; i++){		
	    	   			addDay(inc);
	    	   			inc++;
	    	   		}
   	   		} 
   	   		isSecond = false;
   	   		
	   		}else{
	   			
	   			isSecond = true;
	   			
	   		}
    	  		 
    	  		 
       });
       
       $('#datepicker2').change(function(){

    	   		if(isSecond){ //일까지 선택했을때 
	    	   		var startDate = new Date($('#datepicker1').val());
	    	   		var endDate = new Date($('#datepicker2').val());
	    	   		
	    	   		var ONE_DAY = 1000 * 60 * 60 * 24;
	
	    	   	    // Convert both dates to milliseconds
	    	   	    var date1_ms = startDate.getTime()
	    	   	    var date2_ms = endDate.getTime()
	
	    	   	    // Calculate the difference in milliseconds
	    	   	    var difference_ms = Math.abs(date1_ms - date2_ms)
	
	    	   	    // 시작날짜 마지막날짜 차이
	    	   	    var days = Math.round(difference_ms/ONE_DAY);
	    	   		
	    	   		if(date1_ms > date2_ms){
	    	   			alert('여행 마지막일이 시작일 보다 빠를 수 없습니다.');
	    	   			$('#datepicker1').val('');
	    	   			$('#datepicker2').val('');
	    	   			isSecond = false;
	    	   			return;
	    	   		}
	    	   		
	    	   		if(days > 10){
	    	   			alert('10일을 넘길 수 없습니다.');
	    	   			$('#datepicker1').val('');
	    	   			$('#datepicker2').val('');
	    	   			isSecond = false;
	    	   			return;
	    	   		}

	    	   		var dayChildSize = $('.day-group').find($('.day-item')).length;
	    	   		var inc = 0;
	    	   		
	    	   		if(dayChildSize > (days+1)){ //dayChildSize  = 현재 화면에 추가되어있는 일 수 
	    	   			                         //days+1 = 날짜 차이  
	    	   			var dif = dayChildSize - (days+1) ;
	    	   		
					for(i=0; i< dif; i++){    	   			
		    	   			removeDay();	 // day-group 마지막 요소(날)을 삭제   	   			
		    	   		}
	    	   		} else if(dayChildSize < (days+1)){	//이미 추가되어있는 1일차 이후에 추가 
					var dif = (days+1) - dayChildSize;
	    	   			
	    	   			inc = dayChildSize + 1 ; //현재 요소 다음번 부터 추가 
		    	   		for(i=0; i<dif; i++){		
		    	   			addDay(inc);
		    	   			inc++;
		    	   		}
	    	   		} 
	    	   		isSecond = false;
	    	   		
    	   		}else{
    	   			
    	   			isSecond = true;
    	   			
    	   		}
    	   		
 		});

       
       
       
       
  
  /* 삭제버튼 */
  $('#delete').click(function(){
 	
	 	
	  
  	 		    if( $('#delete').html() == '삭제' ) {
  		
	  		  		$('#delete').html('확인');
	  		 		$('#cancel').show();
	  		  		$('.checkdelete').show();

  	  			} else {
	      		
		    			$('#delete').html('확인');
		    			
		    			var checkList = $('.tour-icon').find('#check');
		    			var isChecked = false;
		    			
			    			for(i = 0; i<checkList.length; i++){
			    				if(checkList.eq(i).is(':checked')){
			    				     var divId = checkList.eq(i).parent('div').parent('div').attr('id');
			    				    $('#'+divId).next().remove();
			    					$('#'+divId).remove();
			    					isChecked = true;
			    				}
			    			}//for 
			    			
			       			if(isChecked == true){
			    				
			    					alert("성공적으로 삭제되었습니다!")
			       			}else {
			       				alert("삭제시키고 싶은 관광지정보를 체크해주세요!")
			       			}
	    		
	      		}//전체 else
	});
 
  
  
  
  //취소버튼 
  $('#cancel').click(function(){
	  $('#cancel').hide();
	  $('#delete').html('삭제');
	  $('.checkdelete').hide();
  });
	  
	  
	  
	  
	
// 도시 관련 목록 뽑아내기 
	$('#nation').change(function click() {
			

		$('#city').empty();
		$('#city').append('<option>선택</option>');
		if($('#nation option:selected').val() == "선택"){
			return; 
		}
		
		
			$.ajax({

				type : "GET", //POST

				dataType : "json",

				url : "/Wellbroad/cityname.do?listcity="+ $('#nation option:selected').val() ,
					//보낼주	
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					//header 정보 
				success : function(data) {

					// data는 서버로부터 전송받은 결과(JSON)이므로 바로 사용한다
					for(i=0; i<data.listcity.length; i++){
						
						$('#city').append('<option>'+data.listcity[i]+'</option>')
						
					}
					
				
				},

				error : function(e) {

					alert('서버 연결 도중 에러가 났습니다. 다시 시도해 주십시오.');

				}

			});
		});
  
  
 //관광지 목록 배열에 넣기  
	  $('#city').change(function click() {
			
			$('#tour').empty();
			$('#tour').append('<option>선택</option>')
			if($('#city option:selected').val() == "선택"){
				/* alert('a'); */
				return; 
			}
			
			
				$.ajax({

					type : "GET",

					dataType : "json",

					url : "/Wellbroad/tourname.do?listtour="+ $('#city option:selected').val() ,
							
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",

					success : function(data) {

						// data는 서버로부터 전송받은 결과(JSON)이므로 바로 사용한다
					
						
 						tourlist[0] = new touritem('airport', 'TP00000', '공항', '도착 및 출발하는 공항 입니다.');
 						$('#tour').append('<option value="'+0+'">공항</option>')
						for(i=0; i< data.listtour.length; i++){
										 //. . ..
							temp = jQuery.parseJSON(data.listtour[i]);
							tourlist[(i+1)] = new touritem(temp.icon, temp.code, temp.name, temp.description);
							
							$('#tour').append('<option value="'+(i+1)+'">'+temp.name+'</option>')
							
						}
						
					
					},

					error : function(e) {

						alert('서버 연결 도중 에러가 났습니다. 다시 시도해 주십시오.');

					}

				});
			});
  
	  

    
/* 팝업창 추가 버튼  ( 관광지 아이콘 추가 ) */  
    $("#modaladd").click(function(){
    		var index = $('#tour option:selected').val();
        var item = tourlist[index];
        var children = $('.tour-icon-list').children();
      	var isAdd = true;
        //관광지 중복요소 검사
        
        if('TP00000' == item.code){
        		isAdd = true;
        }	
        
      	if(isAdd){
		  /*   $('.tour-icon-list').append('<div class="tour-icon" id="icon'+index+'"></div>') */
		                          
		                       //class = tour-icon -> 공통 
		                       //        icon + index -> tourlist[index]를 위한 마킹
		                       //id =    icon + iconIndex (고유ID) -> 아이콘의 아이디 끼리는 서로 중복되지 않는다 PK
		  	targetDIV.append('<div class="tour-icon icon'+index+'" id="icon'+iconIndex+'"></div>'); 
		  	
		  	$('#icon'+iconIndex).append('<img src="${pageContext.request.contextPath}/view/icon/'+item.icon+'.png" class="removeicon" width="180px" height="180px"/>')
	        $('#icon'+iconIndex).append('<div class="removename"><b>'+item.name+'</b></div>')
	        $('#icon'+iconIndex).append('<div class="checkdelete"><input type="checkbox" id="check">삭제</div>')
	        $('.checkdelete').hide()
	    		targetDIV.append('<img class="arrow" src="${pageContext.request.contextPath}/view/icon/arrow.png" width="20px" height="20px"/>')				
	    		iconIndex++;
      	}else{
        		alert("이미 추가된 관광지 입니다.");
        }
    })
    
    $('#save').click(function(){
    	
    			$('#course').val("<%=course%>");
			$('#startdate').val($('#datepicker1').val());
			$('#enddate').val($('#datepicker2').val());
			$('#nationname').val($('#nation option:selected').val());
			$('#cityname').val($('#city option:selected').val());

			var iconlist = $('.day-group').find('.tour-icon-list');
			var iconname = '';
			var icon = '';
			var tourname = '';
			for (i = 0; i < iconlist.length; i++) {
				var iconChild = iconlist.eq(i).find('.tour-icon');

				for (x = 0; x < iconChild.length; x++) {

					index = iconChild.eq(x).attr("class").substr(14); //tour-icon icon0 // 0 -> tourlist[0]

					iconname += tourlist[index].code;
					icon += tourlist[index].icon;
					tourname += tourlist[index].name;
					if (x != (icon.length - 1)) {
						iconname += ',';
						icon += ',';
						tourname += ',';
					}
				}
				if (i != (iconlist.length - 1)) {
					iconname += '|';
					icon += '|';
					tourname += '|';
				}

			}
			$('#iconname').val(iconname); //input 태그 value설정 
			$('#icon').val(icon); //input 태그
			$('#tourname').val(tourname); //input 태그
			$('#myroute').submit();
		})

		//동적으로 추가된 요소에 이벤트 주기
		//$(document).on("click", ".plane", planeEvent);
  
    


    });
