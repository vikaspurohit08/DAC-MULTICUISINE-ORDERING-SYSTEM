import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { SignupaddressComponent } from './signupaddress/signupaddress.component';
import { RestaurantlistComponent } from './restaurantlist/restaurantlist.component';
import { FooditemlistComponent } from './fooditemlist/fooditemlist.component';
import { CartComponent } from './cart/cart.component';
import { HistoryComponent } from './history/history.component';
import { AddrestaurantComponent } from './addrestaurant/addrestaurant.component';
import { ModifyrestaurantComponent } from './modifyrestaurant/modifyrestaurant.component';
import { ViewordersComponent } from './vieworders/vieworders.component';
import { BlockComponent } from './block/block.component';
import { ForgotComponent } from './forgot/forgot.component';
import { OtpComponent } from './otp/otp.component';
import { ChangepasswordComponent } from './changepassword/changepassword.component';
import { AboutusComponent } from './aboutus/aboutus.component';
import { ContactusComponent } from './contactus/contactus.component';
import { TermsandconditionsComponent } from './termsandconditions/termsandconditions.component';
import { DeliveryComponent } from './delivery/delivery.component';
import { PaymentComponent } from './payment/payment.component';
import { AdminhomeComponent } from './adminhome/adminhome.component';
import { ManagerestaurantComponent } from './managerestaurant/managerestaurant.component';
import { CustomerlistComponent } from './customerlist/customerlist.component';
import { AuthGuard } from './auth.guard';


const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forRoot([{path: "signup", component: SignupComponent },
       {path: "login", component: LoginComponent },
      {path : "" , component:LoginComponent },
      {path : "address" , component: SignupaddressComponent },
      {path : "restaurants" , component: RestaurantlistComponent,canActivate:[AuthGuard] },
      {path : "fooditems/:rest_id" , component: FooditemlistComponent,canActivate:[AuthGuard] },
      {path : "cart" , component: CartComponent,canActivate:[AuthGuard] },
      {path : "history" , component: HistoryComponent,canActivate:[AuthGuard] },
      {path : "addrestaurant" , component: AddrestaurantComponent,canActivate:[AuthGuard] },
      {path : "modifyrestaurant/:rest_id" , component: ModifyrestaurantComponent,canActivate:[AuthGuard] },
      {path : "orders" , component: ViewordersComponent,canActivate:[AuthGuard] } ,
      {path : "managecustomers" , component: CustomerlistComponent,canActivate:[AuthGuard] } ,
      {path : "block" , component: BlockComponent,canActivate:[AuthGuard] },
      {path : "delivery" , component: DeliveryComponent,canActivate:[AuthGuard] },
      {path : "payment" , component: PaymentComponent,canActivate:[AuthGuard] },
      {path : "forgot" , component: ForgotComponent },
      {path : "otp" , component: OtpComponent },
      {path : "change" , component: ChangepasswordComponent },
      {path : "aboutUs" , component: AboutusComponent },
      {path : "contactUs" , component: ContactusComponent },
      {path : "termsandconditions" , component: TermsandconditionsComponent },
      { path: "admin" , component:AdminhomeComponent,canActivate:[AuthGuard] },
      { path: "managerestaurants" , component:ManagerestaurantComponent,canActivate:[AuthGuard] }

      ])],
         exports: [RouterModule]
})
export class AppRoutingModule { }
