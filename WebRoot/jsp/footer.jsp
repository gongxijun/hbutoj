<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
 

 <div id="footer">
    Copyright &copy; 2015-2016 <a href="http://www.cnblogs.com/gongxijun/" style="text-decoration: none;" target="_blank">XiJun.Gong</a>. All Rights Reserved. <a href="topic/75" style="text-decoration: none;" target="_blank">Open Source Project</a><br>
	The Programming Contest Web 1.1 platform & Judge Kernel 1.0.2 version.
</div>

<script type="text/javascript">
$(document).ready(function() {	
     OnlineJudge.saveUrl();
     
     scrolltotop.offset(100,120);
	 scrolltotop.init();
 /*
 * User Tip
 * 
 */
$(function(){	
	$(".user-tip").poshytip({	
		className: 'tip-yellowsimple',		
		alignTo: 'target',		
		alignX: 'right',
		alignY: 'bottom',
		offsetX: 5,
		offsetY: -25,			
		content: function(updateCallback) {
			$.post("userInfo.action", { username:$(this).attr('user')} ,function(data){
			updateCallback(data);	
			});

			return "Loading...";		
		}	
	});	
});	
/*End User tip
*/
 });
</script>

