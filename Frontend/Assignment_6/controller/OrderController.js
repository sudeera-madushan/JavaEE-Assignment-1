import {OrderDetail} from "../model/OrderDetail.js";

const cartData="CART";
const cusData="DATA";
const itemData="ITEMS";
function searchCusOrder(){
    let customerId=$('#customerIDOrder').val();
    let customersArr=JSON.parse(localStorage.getItem(cusData));
    let customer=searchCusMethod(customersArr,customerId);
    if (customer!==null){
        $('#customerNameOrder').val(customer._name);
        $('#customerAddressOrder').val(customer._address);
        $('#customerMobileOrder').val(customer._mobile);
        $('#customerSalaryOrder').val(customer._salary);
    }else {

        $('#customerNameOrder').val("");
        $('#customerAddressOrder').val("");
        $('#customerMobileOrder').val("");
        $('#customerSalaryOrder').val("");
        alert("Not Found")
    }

}

function searchCusMethod(arr,id){
    for (const arrElement of arr) {
        if (arrElement._id===id){
            return arrElement;
        }
    }
    return null;
}

function searchItemOrder(){
    let itemCode=$('#itemCodeOrder').val();
    let itemArr=JSON.parse(localStorage.getItem(itemData));
    let item=searchItemMethod(itemArr,itemCode);
    if (item!==null){
        $('#itemNameOrder').val(item._name);
        $('#itemPriceOrder').val(item._price);
        $('#itemQtyOnOrder').val(item._qty);
    }else {
        $('#itemNameOrder').val("");
        $('#itemPriceOrder').val("");
        $('#itemQtyOnOrder').val("");
        alert("Not Found");
    }
}

function searchItemMethod(arr,id){
    for (const arrElement of arr) {
        if (arrElement._id===id){
            return arrElement;
        }
    }
    return null;
}

function reloadCartData(){
    let cart=JSON.parse(localStorage.getItem(cartData));
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

function addToCartArray(){
    let pre_data = localStorage.getItem(cartData);
    let data_arr=[];
    if(pre_data) {
        data_arr = JSON.parse(pre_data);
    }
    let orderDetail = new OrderDetail($('#itemCodeOrder').val(),
        $('#itemNameOrder').val(),
        $('#itemQuentityOrder').val(),
        $('#itemPriceOrder').val(),0);
    if (orderDetail.itemCode && orderDetail.name && orderDetail.qty && orderDetail.price) {
        let index = checkItemRecent(data_arr, orderDetail.itemCode);
        if (-1 !== index) {
            data_arr[index].name=orderDetail.name;
        } else {
            orderDetail.total=orderDetail.price*orderDetail.qty;
            data_arr.unshift(orderDetail);
        }
        // let cart = {
        //     itemCode: itemCode,
        //     name: itemName,
        //     price: itemPrice,
        //     qty: itemQty,
        //     total: itemQty * itemPrice
        // }

    }
    localStorage.setItem(cartData, JSON.stringify(data_arr));
    reloadCartData();

}
function checkItemRecent(arr,id){
    for (let i = 0; i < arr.length; i++) {
        if (arr[i].itemCode===id) {
            return i;
        }
    }
    return -1;
}


$('#searchCusOrder').click(searchCusOrder);
$('#searchItemOrder').click(searchItemOrder);
$('#addToCartBtn').click(addToCartArray);
$('#btn').click(addToCartArray);


reloadCartData();