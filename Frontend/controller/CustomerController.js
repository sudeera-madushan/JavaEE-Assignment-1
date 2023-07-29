import {Customer} from "../model/Customer.js";

const cusData="DATA";
const cusIdRegx=/^(C)([0-9]){3}$/;
const cusNameRegx=/^([A-Za-z]){3,}$/;
const cusAddressRegx=/^([A-Za-z0-9,.]){3,}$/;
const cusMobileRegx = /^0\d{9}$/;
const cusSalaryRegx = /^\d+(,\d{3})*(\.\d{1,2})?$/;
let customerArr=[];
let deleteCustomer=function (i){
    let customers=JSON.parse(localStorage.getItem(cusData));
    customers.map((result, index) => {
        if (result._id===i._id){
            customers.splice(index,1);
        }
    });
    localStorage.setItem(cusData,JSON.stringify(customers));
    loadCustomerData();
}

function addToCustomerArray(){
    let pre_data = localStorage.getItem(cusData);
    let data_arr=[];
    if(pre_data) {
        data_arr = JSON.parse(pre_data);
    }
    let customer = new Customer(0,
        $('#customerNameC').val(),
        $('#customerAddressC').val(),
        $('#customerMobileC').val(),
        parseFloat($('#customerSalaryC').val()));
    // let index =checkCusResentId(data_arr,customer._id);
    // if (index!==-1) {
    //     data_arr[index]._name = $('#customerNameC').val(),
    //         data_arr[index]._address = $('#customerAddressC').val(),
    //         data_arr[index]._mobile = $('#customerMobileC').val(),
    //         data_arr[index]._salary = $('#customerSalaryC').val()
    //     data_arr.splice(index,1,customer)
    // }else {
    //     console.log("+++++++++")
    //     data_arr.unshift(customer);
    // }
    // localStorage.setItem(cusData, JSON.stringify(data_arr));
    // loadCustomerData();

    // let ss={
    //     id:0,
    //     name:"sudeera",
    //     address:"colombo",
    //     mobile:"skdkskfnnsf",
    //     salary:1
    // }
    createAjaxReq(customer);

}
let createAjaxReq = (customer) =>{
    let customer1={
        id:customer._id,
        name:customer._name,
        address:customer._address,
        mobile:customer.mobile,
        salary:customer._salary
    }
    let jStudent = JSON.stringify(customer1);
    $.ajax({

        url: 'http://localhost:8080/pos/customer',
        type:'POST',
        data:customer,
        contentType: 'application/json',
        success:function (response){
            console.log('Student data saved successfully',response)
            loadCustomerData(response)
        },
        error(error){
            console.error('Failed to save student data .Error : ',error)
        }
    })
    loadCustomerData();
}

function checkCusResentId(arr,id){
    for (let i = 0; i < arr.length; i++) {
        if (arr[i]._id===id) {
            return i;
        }
    }
    return -1;
}

function viewCustomersForm(){
    $('#homeSection').css("display","none")
    $('#customerSection').css("display","inherit")
    $('#itemSection').css("display","none")
    $('#orderSection').css("display","none")
}

function loadCustomerData(){
    $.ajax({

        url: 'http://localhost:8080/pos/customer',
        type:'GET',
        contentType: 'application/json',
        success:function (response){
            console.log('Student data get successfully')
            customerArr=[];
            customerArr=(response);
            loadDataTable()
        },
        error(error){
            console.error('Failed to save student data .Error : ',error)
        }
    })

    closeNewCustomer();
}
function loadDataTable(){
    if (customerArr) {
        $('#tableCustomerBody').empty();
        customerArr.map((result, index) => {
            console.log(result);
            var data = `
                <tr>
                    <th scope="row">${result.id}</th>
                    <td>${result.name}</td>
                    <td>${result.address}</td>
                    <td>${result.mobile}</td>
                    <td>${result.salary}</td>
                    <td width="15%">
                        <button class="btn btn-success" id="btn-edite">Edite</button>
                        <button class="btn btn-danger" id="btn-delete">Delete</button>
                    </td>
                </tr>`
            $('#tableCustomerBody').append(data);

        })

    }
}


function closeNewCustomer(){
    $('#newCustomerForm').css({
        transform: "translate(-50%,-50%)scale(0.1)",
        top:"0%",
        // transition: "transform 0.1s,top 0.1s",
        visibility: "hidden"
    })
}

function showNewCustomer(customer) {
    if (!customer){
        customer=new Customer("","","","","");
    }
        $('#customerIDC').val(customer._id);
        $('#customerNameC').val(customer._name);
        $('#customerAddressC').val(customer._address);
        $('#customerMobileC').val(customer._mobile);
        $('#customerSalaryC').val(customer._salary);
    $('#newCustomerForm').css({
        visibility: "visible",
        top:"50%",
        transform: "translate(-50%,-50%)scale(1)",
    });
}


$('#customer-table').on('click','button',(e) =>{
    let id = e.target.id;
    let text = $(e.target).closest('tr').find('th').eq(0).text();
    let customerArr=JSON.parse(localStorage.getItem("DATA"));
    let customer;
    customerArr.map((value, index) => {
        if (value._id===text){
            customer=value;
        }
    });
    if (id==="btn-edite"){
        showNewCustomer(customer);
    }else if(id==="btn-delete"){
        deleteCustomer(customer);
    }
});

$('#customerIDC').on('keyup',(event) =>{
    checkCustomerId(event);
});
function checkCustomerId(event){
    if (cusIdRegx.test($('#customerIDC').val())){
        $('#customerIDC').css({borderColor : "green"})
        if (event.key==='Enter') {
            $('#customerNameC').focus();
        }
        return true;
    }else {
        $('#customerIDC').css({borderColor : "red"})
        return false;
    }
}

$('#customerNameC').on('keyup',(event) =>{
    checkCustomerName(event);
});
function checkCustomerName(event){
    if (cusNameRegx.test($('#customerNameC').val())){
        $('#customerNameC').css({borderColor : "green"})
        if (event.key==='Enter') {
            $('#customerAddressC').focus();
        }
        return true;
    }else {
        $('#customerNameC').css({borderColor : "red"})
        return false;
    }
}

$('#customerAddressC').on('keyup',(event) =>{
   checkCustomerAddress(event);
});
function checkCustomerAddress(event){
    if (cusAddressRegx.test($('#customerAddressC').val())){
        $('#customerAddressC').css({borderColor : "green"})
        if (event.key==='Enter') {
            $('#customerMobileC').focus();
        }
        return true;
    }else {
        $('#customerAddressC').css({borderColor : "red"})
        return false;
    }
}

$('#customerMobileC').on('keyup',(event) =>{
    checkCustomerMobile(event);
});
function checkCustomerMobile(event) {
    if (cusMobileRegx.test($('#customerMobileC').val())){
        $('#customerMobileC').css({borderColor : "green"})
        if (event.key==='Enter') {
            $('#customerSalaryC').focus();
        }
        return true;
    }else {
        $('#customerMobileC').css({borderColor : "red"})
        return false;
    }
}

function checkCustomerSalary(event) {
    if (cusSalaryRegx.test($('#customerSalaryC').val())){
        $('#customerSalaryC').css({borderColor : "green"})
        return true;
    }else {
        $('#customerSalaryC').css({borderColor : "red"})
        return false;
    }
}

$('#customerSalaryC').on('keyup',(event) =>{
    checkCustomerSalary(event);
});

$('#btnSaveNewCustomer').on('click',(event) => {
    if (checkCustomerId(event) & checkCustomerName(event) & checkCustomerAddress(event) & checkCustomerMobile(event) & checkCustomerSalary(event)){
        addToCustomerArray();
    }
});
$('#btnNewCustomer').click(function () {showNewCustomer(null)});
$('#btnCanceNewCustomer').click(closeNewCustomer);
$('#btnViewCustomers').click(viewCustomersForm);
$('#navViewCustomer ').click(viewCustomersForm);
loadCustomerData();
