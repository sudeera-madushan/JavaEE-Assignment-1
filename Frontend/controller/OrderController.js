import {OrderDetail} from "../model/OrderDetail.js";

const cusData="DATA";
const itemData="ITEMS";
let cart=[];

function searchCusOrder(){
    let data={
        id:parseInt($('#customerIDOrder').val())
    }
    createAjaxRequest('customer','GET',data,setToCusFields)


}

function setToCusFields(customer){
    if (customer!==null){
        $('#customerNameOrder').val(customer.name);
        $('#customerAddressOrder').val(customer.address);
        $('#customerMobileOrder').val(customer.mobile);
        $('#customerSalaryOrder').val(customer.salary);
    }else {

        $('#customerNameOrder').val("");
        $('#customerAddressOrder').val("");
        $('#customerMobileOrder').val("");
        $('#customerSalaryOrder').val("");
        alert("Not Found")
    }
}

function searchItemOrder(){
    let itemCode=$('#itemCodeOrder').val();
    // let itemArr=JSON.parse(localStorage.getItem(itemData));
    // let item=searchItemMethod(itemArr,itemCode);
    let data={
        id:parseInt(itemCode)
    }
    createAjaxRequest('item','GET',data,setItemFields)

}

function setItemFields(item){

    if (item!==null){
        $('#itemNameOrder').val(item.name);
        $('#itemPriceOrder').val(item.price);
        $('#itemQtyOnOrder').val(item.qty);
    }else {
        $('#itemNameOrder').val("");
        $('#itemPriceOrder').val("");
        $('#itemQtyOnOrder').val("");
        alert("Not Found");
    }
}

function reloadCartData(){
    // let cart=JSON.parse(localStorage.getItem(cartData));
    $('#tableCartBody').empty();
    cart.map((object,index) =>{
        var data = `
                <tr>
                    <th scope="row">${object._itemCode}</th>
                    <td>${object._name}</td>
                    <td>${object._price}</td>
                    <td>${object._qty}</td>
                    <td>${object._total}</td>

                    <td width="15%">
                        <button class="btn btn-success" >Edite</button>
                        <button class="btn btn-danger" ">Delete</button>
                    </td>
                </tr>`
        $('#tableCartBody').append(data);
    });
}

function setTotal() {
    let total=0;
    cart.map((value) => {
        total+=value._total;
    })
    $('#totalAll').empty();
    $('#totalAll').append("Total :     "+(total));
}

function addToCartArray(){
    let items =[];
        items=JSON.parse(localStorage.getItem("ITEMS"));
    var itemRecent;

    let orderDetail = new OrderDetail($('#itemCodeOrder').val(),
        $('#itemNameOrder').val(),
        $('#itemPriceOrder').val(),
        $('#itemQuentityOrder').val(),
        0);
    for (let i = 0; i < items.length; i++) {
        if (items[i]._id===orderDetail._itemCode) {
            itemRecent = items[i];
        }
    }

    if (orderDetail._itemCode && orderDetail._name && orderDetail._qty && orderDetail._price) {
        let index = checkItemRecent(cart, orderDetail._itemCode);
        if (-1 !== index) {
            let cartQTY = parseFloat(cart[index]._qty)+parseFloat(orderDetail._qty);
            if (itemRecent._qty>=cartQTY){
                cart[index]._qty=cartQTY;
                cart[index]._total=parseFloat(cart[index]._qty)*parseFloat(orderDetail._price);
            }else {
                alert("Low Quantity")
            }

        } else {
            if (itemRecent._qty>=orderDetail._qty){
                orderDetail._total=orderDetail._price*orderDetail._qty;
                cart.unshift(orderDetail);
            }else {
                alert("Low Quantity")
            }

        }

    }
    reloadCartData();
    setTotal();
}
function checkItemRecent(arr,id){

    for (let i = 0; i < arr.length; i++) {
        if (arr[i]._itemCode===id) {
            return i;
        }
    }
    return -1;
}

function addInvoiceDetails() {
    $('#tableincvoiceBody').empty();
    cart.map((object,index) =>{
        var data = `
                <tr>
                    <th scope="row">${object._itemCode}</th>
                    <td>${object._name}</td>
                    <td>${object._price}</td>
                    <td>${object._qty}</td>
                    <td>${object._total}</td>
                </tr>`
        $('#tableincvoiceBody').append(data);
    });
    $('#invoice-order-id').empty();
    $('#invoice-order-id').append("Order ID : "+$('#orderID').val());
    $('#invoice-customer-id').empty();
    $('#invoice-customer-id').append("Customer ID : "+$('#customerIDOrder').val());
    $('#invoice-customer-name').empty();
    $('#invoice-customer-name').append("Customer Name : "+$('#customerNameOrder').val());
    $('#invoice-order-date').empty();
    $('#invoice-order-date').append("Date : "+$('#date').val());
    $('.invoice').css({display: 'inherit'})
}

function purchaseOrder() {
    let itemArr=JSON.parse(localStorage.getItem(itemData));
    cart.map((values => {
        for (let i = 0; i < itemArr.length; i++) {
            if (values._itemCode===itemArr[i]._id){
                itemArr[i]._qty=parseFloat(itemArr[i]._qty)-parseFloat(values._qty);
            }
        }
    }));
    localStorage.setItem("ITEMS",JSON.stringify(itemArr));
    addInvoiceDetails();
    cart=[];
    reloadCartData();
}

$('#searchCusOrder').click(searchCusOrder);
$('#searchItemOrder').click(searchItemOrder);
$('#addToCartBtn').click(addToCartArray);
$('#btn').click(addToCartArray);


$('#purchase-order-btn').click(purchaseOrder);

function closeInvoice() {
    $('.invoice').css({display:'none'})
}

$('#close-invoice').click(closeInvoice)

reloadCartData();