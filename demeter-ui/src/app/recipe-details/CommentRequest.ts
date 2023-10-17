export class CommentRequest{

  constructor(
    public text: string,
    public author: string,
    public likeCount: number,
    public disLikeCount: number
  ) {
  }
}
