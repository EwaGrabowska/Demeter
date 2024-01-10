import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatPaginatorIntl, PageEvent} from "@angular/material/paginator";
import {PolishMatPaginatorIntl} from "../home/polishMatPaginatorIntl";
import {UserService} from "../recipe-details/user.service";

@Component({
  selector: 'app-subscribtions',
  templateUrl: './subscribtions.component.html',
  styleUrls: ['./subscribtions.component.css'],
  providers: [
    { provide: MatPaginatorIntl, useValue: new PolishMatPaginatorIntl() }
  ]
})
export class SubscribtionsComponent implements OnInit{
  subscribedUsers!: string[] | undefined;
  paginatedUsers!: string[] | undefined;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  length: number | undefined = 0 ;
  pageSize = 3;
  pageIndex = 0;
  disabled = false;

  constructor(private authService: UserService) { }

  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe(user =>{
      this.subscribedUsers = user?.subscribedAuthors;
      this.length = user?.subscribedAuthors.length;
    })
  }
  updatePaginatedUsers(): void {
    const startIndex = this.pageIndex * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    this.paginatedUsers = this.subscribedUsers?.slice(startIndex, endIndex);
    this.length = this.subscribedUsers?.length;
  }

  handlePageEvent(event: PageEvent) {
    this.pageIndex = event.pageIndex;
    this.updatePaginatedUsers();
  }
}
