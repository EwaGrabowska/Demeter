export class UserResponse{

  constructor(
    public id: number,
    public firstName: string,
    public lastName: string,
    public fullName: string,
    public picture: string,
    public emailAddress: string,
    public sub: string,
    public subscribedAuthors: string[],
    public videoHistory: Number[],
    public likedRecipe: Number[],
    public disLikedRecipe: Number[]
  ) {
  }
}
