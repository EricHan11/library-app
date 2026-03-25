import BookModel from "./BookModel";

//Object we expect to receive from the backend
class ShelfCurrentLoans {
    book: BookModel;
    daysLeft: number;

    constructor(book: BookModel, daysLeft: number) {
        this.book = book;
        this.daysLeft = daysLeft;
    }

}

export default ShelfCurrentLoans;