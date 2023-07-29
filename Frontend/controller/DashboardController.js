
function viewHomeForm(){
    $('#homeSection').css("display","inherit")
    $('#customerSection').css("display","none")
    $('#itemSection').css("display","none")
    $('#orderSection').css("display","none")
}
function viewItemForm(){
    $('#homeSection').css("display","none")
    $('#customerSection').css("display","none")
    $('#orderSection').css("display","none")
    $('#itemSection').css("display","inherit")
}
function viewOrderForm(){
    $('#homeSection').css("display","none")
    $('#customerSection').css("display","none")
    $('#itemSection').css("display","none")
    $('#orderSection').css("display","inherit")
}



$('#navViewHome').click(viewHomeForm);
$('#navViewItem').click(viewItemForm);
$('#navViewOrders').click(viewOrderForm);
$('#btnViewItems').click(viewItemForm);
$('#btnViewOrders').click(viewOrderForm);
