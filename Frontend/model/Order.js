class Order{
    get id() {
        return this._id;
    }

    set id(value) {
        this._id = value;
    }

    get date() {
        return this._date;
    }

    set date(value) {
        this._date = value;
    }

    get orderDetails() {
        return this._orderDetails;
    }

    set orderDetails(value) {
        this._orderDetails = value;
    }

    get total() {
        return this._total;
    }

    set total(value) {
        this._total = value;
    }

    get name() {
        return this._name;
    }

    set name(value) {
        this._name = value;
    }
    constructor(id,date,orderDetails,total) {
        this._id=id;
        this._name=name;
        this._orderDetails=orderDetails;
        this._total=total;
        this._date = date;


    }
}