import {Component, Input, OnInit} from '@angular/core';
import {CommentRequest} from "./commentRequest";
import {FormControl, FormGroup} from "@angular/forms";
import {UserService} from "../recipe-details/user.service";
import {CommentsService} from "./comments.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {CommentResponse} from "./commentResponse";


@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit{

  @Input()
  recipeId: any = '';
  commentsForm: FormGroup;
  showCommentSection: boolean = true;
  comments: CommentResponse[] = [];

  constructor(private userService: UserService, private commentsService: CommentsService, private matSnackBar: MatSnackBar) {

    this.commentsForm = new FormGroup({
      comment: new FormControl('comment')
    })
  }

  ngOnInit(): void {
    this.getComments()
  }

  getComments(){
    this.commentsService.getAllComments(this.recipeId).subscribe(data => {
      this.comments = data;
    })
  }

  showCommentButton() {

  }

  postComment() {
    const comment = this.commentsForm.get('comment')?.value;
    const userSub = this.userService.getUserSub();
    const commentRequest: CommentRequest = new class implements CommentRequest {
      author: string = userSub;
      text: string = comment;
    }
    this.commentsService.addComment(commentRequest, this.recipeId).subscribe({
      next: response => {
        console.log('Form submitted successfully!', response);
        this.matSnackBar.open("komentarz dodany pomyślnie", "Ok")
        this.clear();
        this.getComments();
      },
      error: error => {
        console.error('An error occurred while submitting the form:', error);
      }
    });
  }

  clear() {
    this.commentsForm.reset();
  }


}
