import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import { FormsModule } from '@angular/forms';
import {UserService} from "../recipe-details/user.service";
import {UserResponse} from "../recipe-details/UserResponse";
import {OidcSecurityService} from "angular-auth-oidc-client";
import {FileSystemFileEntry, NgxFileDropComponent, NgxFileDropEntry} from "ngx-file-drop";
import {Location} from "@angular/common";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-profile-edit',
  templateUrl: './profile-edit.component.html',
  styleUrls: ['./profile-edit.component.css']
})
export class ProfileEditComponent implements OnInit{
  fullName: string | undefined;
  newFullName?: string;
  picture: string | undefined | File;
  previewUserImage: string | undefined;
  profileForm: FormGroup;
  public files: NgxFileDropEntry[] = [];
  @ViewChild
  (NgxFileDropComponent) fileDrop: NgxFileDropComponent | undefined;

  constructor(private location: Location, private oidcSecurityService: OidcSecurityService,
              private formBuilder: FormBuilder, private userService: UserService, private router: Router, private matSnackBar: MatSnackBar) {
    this.profileForm = this.formBuilder.group({
      fullName: ['', Validators.minLength(3)]
    });
  }

  ngOnInit(): void {
    this.oidcSecurityService.isAuthenticated$.subscribe(({isAuthenticated}) =>{
      this.userService.registerUser();
    })
    this.userService.getCurrentUser().subscribe(user =>{
      this.newFullName = user?.fullName;
      this.picture = user?.picture;
      this.previewUserImage = user?.picture;
    });
  }

  goBack() {
    this.location.back();
  }

  async onSubmit() {
    if (this.profileForm.valid){
      if (this.newFullName){
        this.userService.updateUser(this.picture, this.newFullName).then(
            () => {
              console.log('User hac been updated');
              this.matSnackBar.open("Dane użytkownika zapisane pomyślnie", "Ok")
                  .afterDismissed().subscribe(()=>{
                    this.userService.triggerCurrentUser();
                    this.router.navigate(['/']);
                  }
              )
            }, error =>{
              console.error('An error occurred while submitting the form:', error);
            }
        )
      }
    }
  }

  private previewImage(file: File) {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      this.previewUserImage = reader.result as string;
    };
  }

  deletePhoto() {
    this.picture = undefined;
    this.previewUserImage = undefined;
  }

  onNameChange(event: any) {
    this.newFullName = event.target.value;
  }

  openFileSelector() {
    if (this.fileDrop) {
      this.fileDrop.openFileSelector();
    }
  }

  dropped(files: NgxFileDropEntry[]) {
    this.files = files;

    for (const droppedFile of files) {
      if (droppedFile.fileEntry.isFile) {
        const fileEntry = droppedFile.fileEntry as FileSystemFileEntry;
        fileEntry.file((file: File) => {
          this.picture = file;
          this.previewImage(file);
        });
      }
    }
  }
}
