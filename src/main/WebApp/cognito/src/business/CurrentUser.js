
export class CurrentUser{
    constructor(username){
        if(username.includes("eoghan2014") || username.includes("Eoghan2014")){
            this.username = username.substring(
                username.indexOf("+") + 1, 
                username.lastIndexOf("@")
            );
        }
        else{
            this.username = username;
        }
    }
}