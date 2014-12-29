run(){
 play run
}


post(){
curl --header "Content-type: application/json" --request POST --data '{"name": "Stupid Dream", "brand": 2, "marketplace" : 2, "price" : 800, "created" : 1419783519}' "http://localhost:9000/product/add"
}

post
