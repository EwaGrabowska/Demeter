import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {SubscriptionsPageService} from "./subscriptions-page.service";
import {RecipeResponse} from "../home/recipeResponse";

@Component({
  selector: 'app-single-subscription',
  templateUrl: './single-subscription.component.html',
  styleUrls: ['./single-subscription.component.css']
})
export class SingleSubscriptionComponent implements OnInit, OnChanges {

  @Input()
  userSub!: string;
  fullName!: string;
  subscribedUserRecipes!: RecipeResponse[]

  pageSize: number = 3;
  pageNumber: number = 0;
  length!: number;
  showMoreButton: boolean = false;
  showLessButton: boolean = false;

  constructor(private singleSubscriptionservice: SubscriptionsPageService) { }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['userSub'] && changes['userSub'].currentValue) {
      this.loadUserData();
    }
  }

  ngOnInit() {
  }

  private loadUserData() {
    this.singleSubscriptionservice.getUserBySub(this.userSub).subscribe(user => {
      this.fullName = user.fullName;
    });

    this.loadRecipes();
  }

  private loadRecipes() {
    this.singleSubscriptionservice.getSubscribedUserRecipe(this.pageSize, this.pageNumber, this.userSub).subscribe(data => {
      this.subscribedUserRecipes = data.content;
      this.length = data.totalElements;
      this.showMoreButton = this.length > (this.pageNumber + 1) * this.pageSize;
    });
  }

  showMore() {
    this.pageNumber++;
    this.loadRecipes();
    this.showLessButton = true;
    this.showMoreButton = this.length > (this.pageNumber + 1) * this.pageSize;
  }

  showLess() {
    this.pageNumber--;
    this.loadRecipes();

    if (this.pageNumber === 0) {
      this.showLessButton = false;
    }
  }

}
