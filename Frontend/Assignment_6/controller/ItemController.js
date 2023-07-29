import {Item} from "../model/Item.js";
const itemData="ITEMS";

const itemCodeRegx=/^(I)([0-9]){3}$/;
const itemNameRegx=/^([A-Za-z]){3,}$/;
const itemQTYRegx=/^([0-9]){1,}$/;
const itemPriceRegx = /^\d+(,\d{3})*(\.\d{1,2})?$/;

let loadItemData= function (){
    let pre_data=localStorage.getItem(itemData);
    let item_data_arr=JSON.parse(pre_data);

    if (item_data_arr) {
        $('#tableItemBody').empty();
        item_data_arr.map((result, index) => {
            var data = `
                <tr>
                    <th scope="row">${result._id}</th>
                    <td>${result._name}</td>
                    <td>${result._qty}</td>
                    <td>${result._price}</td>
                    <td width="15%">
                        <button class="btn btn-success"  id="btn-edite">Edite</button>
                        <button class="btn btn-danger" id="btn-delete">Delete</button>
                    </td>
                </tr>`
            $('#tableItemBody').append(data);

        })

    }
    closeNewItem();
}

function showNewItem(item){
    if (!item){
        item=new Item("","","","");
    }
        $('#itemCodeI').val(item._id);
        $('#itemNameI').val(item._name);
        $('#itemQTYI').val(item._qty);
        $('#itemPriceI').val(item._price);
    $('#newItemForm').css({
        visibility: "visible",
        top:"50%",
        transform: "translate(-50%,-50%)scale(1)",
    })
}

function deleteItem(item){
    let items=JSON.parse(localStorage.getItem(itemData));
    items.map((value, index) => {
        if (value._id===item._id){
            items.splice(index,1);
        }
    })
    localStorage.setItem(itemData,JSON.stringify(items));
    loadItemData();
}

function addToItemArray(){
    let pre_data = localStorage.getItem(itemData);
    let data_arr=[];
    if(pre_data) {
        data_arr = JSON.parse(pre_data);
    }
    let item = new Item($('#itemCodeI').val(),
            $('#itemNameI').val(),
            $('#itemQTYI').val(),
            $('#itemPriceI').val());
    let index =checkItemRecent(data_arr,item._id);
    if (index!=-1) {
        data_arr.splice(index,1,item)
    }else {
        data_arr.unshift(item);
    }
    localStorage.setItem(itemData, JSON.stringify(data_arr));
    loadItemData();
}

function checkItemRecent(arr,id){
    for (let i = 0; i < arr.length; i++) {
        if (arr[i]._id===id) {
            return i;
        }
    }
    return -1;
}



function closeNewItem(){
    $('#newItemForm').css({
        transform: "translate(-50%,-50%)scale(0.1)",
        top:"0%",
        // transition: "transform 0.1s,top 0.1s",
        visibility: "hidden"
    })
}

$('#item-table').on('click','button',(e) =>{
    let id = e.target.id;
    let text = $(e.target).closest('tr').find('th').eq(0).text();
    let itemArray=JSON.parse(localStorage.getItem(itemData));
    let item;
    itemArray.map((value, index) => {
        if (value._id===text){
            item=value;
        }
    });
    if (id==="btn-edite"){
        showNewItem(item);
    }else if(id==="btn-delete"){
        deleteItem(item);
    }
});


$('#itemCodeI').on('keyup',(event) =>{
    checkItemCode(event);
});
function checkItemCode(event) {
    if (itemCodeRegx.test($('#itemCodeI').val())) {
        $('#itemCodeI').css({borderColor: "green"})
        if (event.key === 'Enter') {
            $('#itemNameI').focus();
        }
        return true;
    } else {
        $('#itemCodeI').css({borderColor: "red"})
        return false;
    }
}
function checkItemName(event) {
    if (itemNameRegx.test($('#itemNameI').val())) {
        $('#itemNameI').css({borderColor: "green"})
        if (event.key === 'Enter') {
            $('#itemQTYI').focus();
        }
        return true;
    } else {
        $('#itemNameI').css({borderColor: "red"})
        return false;
    }
}
function checkItemQTY(event){
    if (itemQTYRegx.test($('#itemQTYI').val())){
        $('#itemQTYI').css({borderColor : "green"})
        if (event.key==='Enter') {
            $('#itemPriceI').focus();
        }
        return true;
    }else {
        $('#itemQTYI').css({borderColor : "red"})
        return false;
    }
}
function checkItemPrice(event){
    if (itemPriceRegx.test($('#itemPriceI').val())){
        $('#itemPriceI').css({borderColor : "green"})
        return true;
    }else {
        $('#itemPriceI').css({borderColor : "red"})
        return false;
    }
}

$('#itemNameI').on('keyup',(event) =>{
    checkItemName(event);
});

$('#itemQTYI').on('keyup',(event) =>{
    checkItemQTY(event);
});

$('#btnSaveNewItem').on('click',(event) =>{
    if (checkItemCode(event) & checkItemName(event) & checkItemQTY(event) & checkItemPrice(event)){
        addToItemArray();
    }
});
$('#itemPriceI').on('keyup',(event) =>{
   checkItemPrice(event);
});

// $('#customerNameC').on('keyup',(event) =>{
//     if (cusNameRegx.test($('#customerNameC').val())){
//         $('#customerNameC').css({borderColor : "green"})
//         if (event.key==='Enter') {
//             $('#customerAddressC').focus();
//         }
//     }else {
//         $('#customerNameC').css({borderColor : "red"})
//     }
// });
//
// $('#customerAddressC').on('keyup',(event) =>{
//     if (cusAddressRegx.test($('#customerAddressC').val())){
//         $('#customerAddressC').css({borderColor : "green"})
//         if (event.key==='Enter') {
//             $('#customerMobileC').focus();
//         }
//     }else {
//         $('#customerAddressC').css({borderColor : "red"})
//     }
// });
//
// $('#customerMobileC').on('keyup',(event) =>{
//     if (cusMobileRegx.test($('#customerMobileC').val())){
//         $('#customerMobileC').css({borderColor : "green"})
//         if (event.key==='Enter') {
//             $('#customerSalaryC').focus();
//         }
//     }else {
//         $('#customerMobileC').css({borderColor : "red"})
//     }
// });
$('#btnNewItem').click(showNewItem);
$('#btnCancelNewItem').click(closeNewItem);


loadItemData();