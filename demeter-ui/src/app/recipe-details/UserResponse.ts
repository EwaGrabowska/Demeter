export class UserResponse{

  constructor(
    public id: number,
    public firstName: string,
    public lastName: string,
    public fullName: string,
    public picture: string,
    public emailAddress: string,
    public sub: string,
    public subscribedChannelId: Set<number>,
    public videoHistory: Set<number>,
    public likedRecipe: Set<number>,
    public disLikedRecipe: Set<number>
  ) {
  }
}
