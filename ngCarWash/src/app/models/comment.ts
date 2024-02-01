import { Store } from "./store";
import { User } from "./user";

// comment.model.ts
export class Comment {
    id: number;
    content: string;
<<<<<<< HEAD
    store: Store;
    user: User;
=======

    user : User;
    store : Store;
>>>>>>> 08e5472139942f9b226f09fb2fd73f9a4e5906c3
    commentDate: Date | null;  // Allow Date or null

    constructor(
      id: number = 0,
      content: string = '',
<<<<<<< HEAD
      store: Store = new Store(),
      user: User = new User(),
=======

      user : User = new User(),
      store : Store = new Store(),
>>>>>>> 08e5472139942f9b226f09fb2fd73f9a4e5906c3
      commentDate: Date | null = null
    ) {
      this.id = id;
      this.content = content;
<<<<<<< HEAD
      this.store = store;
      this.user = user;
=======

      this.user = user;
      this.store = store;
>>>>>>> 08e5472139942f9b226f09fb2fd73f9a4e5906c3
      this.commentDate = commentDate || null;  // Ensure a non-nullable default value
    }
  }
