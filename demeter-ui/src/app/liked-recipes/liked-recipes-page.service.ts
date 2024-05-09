import {Injectable, OnInit} from '@angular/core';
import {environment} from "../../environments/environment";
import {BehaviorSubject, Observable, switchMap, timer} from "rxjs";
import {RecipeResponse} from "../home/recipeResponse";
import {HttpClient, HttpParams} from "@angular/common/http";
import {UserService} from "../recipe-details/user.service";
import {RecipePage} from "../home/recipePage";

@Injectable({
  providedIn: 'root'
})
export class LikedRecipesPageService implements OnInit{

  private apiUrl = environment.apiUrl;
  private myRecipesSubject$: BehaviorSubject<RecipeResponse[]> = new BehaviorSubject<RecipeResponse[]>([]);
  private refreshTrigger$: BehaviorSubject<void> = new BehaviorSubject<void>(undefined);
  private refreshInterval: number = 60000;

  page: number = 1;
  size: number = 9;

  likedRecipeIdList: Number[] | undefined;

  constructor(private http: HttpClient, private authService: UserService) {
    authService.getCurrentUser().subscribe(user =>{
      this.likedRecipeIdList = user?.likedRecipe
    });
  }

  ngOnInit() {
    this.startBackgroundRefreshing();
  }

  triggerRefresh(): void {
    this.refreshTrigger$.next(undefined);
  }

  private getAllRecipesPega(pageIndex: number, pageSize: number): Observable<any> {
    const params = new HttpParams()
      .set('page', pageIndex.toString())
      .set('size', pageSize.toString());

    return this.http.post<RecipePage<RecipeResponse>>(`${this.apiUrl}recipes/likedrecipe/paginated`, this.likedRecipeIdList, {params});
  }

  private startBackgroundRefreshing(): void {
    timer(0, this.refreshInterval)
      .pipe(
        switchMap(() => this.refreshTrigger$)
      )
      .subscribe(() => {
        this.getAllRecipesPega(this.page, this.size).subscribe((recipes) => {
          this.myRecipesSubject$.next(recipes);
        });
      });
  }

  getLikedRecipes(pageIndex: number, pageSize: number): Observable<any>{
    this.getAllRecipesPega(pageIndex, pageSize, ).subscribe(recipes =>{
      this.myRecipesSubject$.next(recipes);
    })
    this.page = pageIndex;
    this.size = pageSize;
    return this.myRecipesSubject$;
  }

}
