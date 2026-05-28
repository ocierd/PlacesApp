import { Injectable, ValueProvider } from "@angular/core";
import { MAT_PAGINATOR_DEFAULT_OPTIONS, MatPaginatorIntl } from "@angular/material/paginator";




export const PLACES_PAGINATION_DEFAULT_OPTIONS_PROVIDER:
ValueProvider = {
  provide: MAT_PAGINATOR_DEFAULT_OPTIONS,
  useValue: {
    length: 100,
    pageSize: 10,
    pageSizeOptions: [5, 10, 25,50, 100],
    ariaLabel: 'Select page'
  }
};



@Injectable()
export class MatPaginatorIntlEs extends MatPaginatorIntl {

    constructor() {
        super();

        this.firstPageLabel = 'Primera página';
        this.itemsPerPageLabel = 'Elementos por página';
        this.lastPageLabel = 'Última página';
        this.nextPageLabel = 'Página siguiente';
        this.previousPageLabel = 'Página anterior';



    }


    override getRangeLabel = (page: number, pageSize: number, length: number): string => {
        if (length === 0 || pageSize === 0) {
            return `0 de ${length}`;
        }
        const startIndex = page * pageSize;
        const endIndex = startIndex < length ?
            Math.min(startIndex + pageSize, length) :
            startIndex + pageSize;
        return `${startIndex + 1} - ${endIndex} de ${length}`;
    }

}