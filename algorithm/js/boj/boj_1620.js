const fs = require('fs')
const inputs = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const [N, M] = inputs[0].split(' ').map(Number)
const pokemons = inputs.slice(1, N + 1)
const guesses = inputs.slice(N + 1)

const pokemonsMap = new Map()

for (let i = 0; i < N; i++) {
  pokemonsMap.set(pokemons[i], i + 1)
  pokemonsMap.set((i + 1).toString(), pokemons[i])
}

const res = []

for (let i = 0; i < M; i++) {
  res.push(pokemonsMap.get(guesses[i]))
}

console.log(res.join('\n'))
