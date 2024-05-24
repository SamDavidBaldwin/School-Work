function getNewPokemon() {
    value = Math.floor(Math.random() * 890)
	var url = "https://pokeapi.co/api/v2/pokemon/" + value

	console.log("making fetch to", url)
	
	fetch(url)
		.then(resp=>{return resp.json()})
		.then(json=>{
			console.log(json["sprites"])
        

			document.getElementById("pokemon").src = json.sprites.front_default
            val = json.name
            val = val.charAt(0).toUpperCase() + val.slice(1)
            document.getElementById("pokemon").alt = val
		})

}


document.addEventListener("DOMContentLoaded", () => {
  console.log("Hello World!");
});