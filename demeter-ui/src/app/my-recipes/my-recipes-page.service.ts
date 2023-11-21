import {Injectable, OnDestroy, OnInit} from '@angular/core';
import {environment} from "../../environments/environment";
import {BehaviorSubject, Observable, switchMap, timer} from "rxjs";
import {RecipeResponse} from "../home/recipeResponse";
import {HttpClient, HttpParams} from "@angular/common/http";
import {RecipePage} from "../home/recipePage";
import {UserService} from "../recipe-details/user.service";

@Injectable({
  providedIn: 'root'
})
export class MyRecipesPageService implements OnInit{

  private apiUrl = environment.apiUrl;
  private myRecipesSubject$: BehaviorSubject<RecipeResponse[]> = new BehaviorSubject<RecipeResponse[]>([]);
  private refreshTrigger$: BehaviorSubject<void> = new BehaviorSubject<void>(undefined);
  private refreshInterval: number = 60000;

  page: number = 1;
  size: number = 9;

  userSub: string;

  constructor(private http: HttpClient, private authService: UserService) {
    this.userSub = authService.getUserSub();
  }

  ngOnInit() {
    this.startBackgroundRefreshing();
  }

  triggerRefresh(): void {
    this.refreshTrigger$.next(undefined);
  }

  private getAllRecipesPega(pageIndex: number, pageSize: number, userSub: string): Observable<any> {
    const params = new HttpParams()
      .set('page', pageIndex.toString())
      .set('size', pageSize.toString())
      .set('usersub', userSub);

    return this.http.get<RecipePage<RecipeResponse>>(`${this.apiUrl}recipes/myrecipes/paginated`, {params});
  }

  private startBackgroundRefreshing(): void {
    timer(0, this.refreshInterval)
      .pipe(
        switchMap(() => this.refreshTrigger$)
      )
      .subscribe(() => {
        this.getAllRecipesPega(this.page, this.size, this.userSub).subscribe((recipes) => {
          this.myRecipesSubject$.next(recipes);
        });
      });
  }

  getMyRecipes(pageIndex: number, pageSize: number): Observable<any>{
    this.getAllRecipesPega(pageIndex, pageSize, this.userSub).subscribe(recipes =>{
      this.myRecipesSubject$.next(recipes);
    })
    this.page = pageIndex;
    this.size = pageSize;
    return this.myRecipesSubject$;
  }

}
