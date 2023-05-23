import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
 providedIn: 'root'
})
export class ChatService {
 private url: string;

 constructor(private http: HttpClient) {
   this.url = `http://localhost:8082/api/${localStorage.getItem('fakeUserId')}`;
 }

 public send(message: string): Observable<string> {
   return this.http.put<string>(this.url, message);
 }

 public findAll() {
   return this.http.get<any>(this.url);
 }

 public deleteConversation() {
   this.http.delete(this.url).subscribe(data => {
   });
 }
}
