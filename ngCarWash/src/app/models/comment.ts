import { Store } from "./store";
import { User } from "./user";

// comment.model.ts
export class Comment {
    id: number;
    content: string;
    store: Store;
    user: User;
    commentDate: Date | null;  // Allow Date or null

    constructor(
      id: number = 0,
      content: string = '',
      store: Store = new Store(),
      user: User = new User(),
      commentDate: Date | null = null
    ) {
      this.id = id;
      this.content = content;
      this.store = store;
      this.user = user;
      this.commentDate = commentDate || null;  // Ensure a non-nullable default value
    }
  }
