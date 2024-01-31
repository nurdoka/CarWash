// comment.model.ts
export class Comment {
    id: number;
    content: string;
    storeId: number;
    userId: number;
    commentDate: Date | null;  // Allow Date or null
  
    constructor(
      id: number = 0,
      content: string = '',
      storeId: number = 0,
      userId: number = 0,
      commentDate: Date | null = null
    ) {
      this.id = id;
      this.content = content;
      this.storeId = storeId;
      this.userId = userId;
      this.commentDate = commentDate || null;  // Ensure a non-nullable default value
    }
  }
  