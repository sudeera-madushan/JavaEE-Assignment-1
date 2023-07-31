let createAjaxRequest = (servlet,method,object,recol) =>{
    let re;
    $.ajax({
        url: 'http://localhost:8080/pos/'+servlet,
        type:method,
        data:object,
        contentType: 'application/json',
        success:function (response){
            console.log('Response',response)
            // if (servlet==='customer'){
                recol(response);
                re=response;
            // }
        },
        error(error){
            console.error('Failed to save data .Error : ',error)
            return false;
        }
    })
    return re;
}