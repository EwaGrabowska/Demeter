import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {RecipeResponse} from "../home/recipeResponse";
import {Subscription} from "rxjs";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {SketchesPageService} from "./sketches-page.service";

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnDestroy, OnInit {
  parentComponent: string = 'notes';
  sketches: Array<RecipeResponse> = [];
  getAllSketchesSubscription!: Subscription;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  length = 0;
  pageSize = 9;
  pageIndex = 0;
  pageSizeOptions = [9, 16];
  hidePageSize = false;
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  disabled = false;
  userSub!: string;

  constructor(private sketchesPageService: SketchesPageService) {
  }

  ngOnInit(): void {
    this.getAllSketchesSubscription = this.sketchesPageService.getSketches(this.pageIndex, this.pageSize)
      .subscribe(data => {
        this.sketches = data.content;
        this.length = data.totalElements;
      });
  }

  ngOnDestroy(): void {
    this.getAllSketchesSubscription.unsubscribe();
  }

  handlePageEvent(event: PageEvent) {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;

    this.sketchesPageService.getSketches(this.pageIndex, this.pageSize)
      .subscribe(data => {
        this.sketches = data.content;
        this.length = data.totalElements;
      });
  }
}
