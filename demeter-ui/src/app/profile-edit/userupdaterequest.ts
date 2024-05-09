export class Userupdaterequest {
    constructor(
        public fullname: string,
        public currentusersub: string,
        public photourl: string) {
    }

    setPhotoURL(photoUrl: string){
        this.photourl = photoUrl;
    }

}
