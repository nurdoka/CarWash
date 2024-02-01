import { Store } from "./store";
import { User } from "./user";

// comment.model.ts
export class Comment {
    id: number;
    content: string;

    user : User;
    store : Store;
    commentDate: Date | null;  // Allow Date or null
  
    constructor(
      id: number = 0,
      content: string = '',

      user : User = new User(),
      store : Store = new Store(),
      commentDate: Date | null = null
    ) {
      this.id = id;
      this.content = content;

      this.user = user;
      this.store = store;
      this.commentDate = commentDate || null;  // Ensure a non-nullable default value
    }
  }
  