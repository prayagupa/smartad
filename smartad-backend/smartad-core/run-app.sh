run(){
 play run
}


post(){
curl --header "Content-type: application/json" --request POST --data '{"name": "Stupid Dream", "brand": 32}' "http://localhost:9000/product/add"
}

post
