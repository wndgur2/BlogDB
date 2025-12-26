const fs = require('fs')

const inputs = fs.readFileSync('/dev/stdin').toString().trim().split('\n').map(Number).slice(1)
// const inputs = fs.readFileSync('inputs').toString().trim().split('\n').map(Number).slice(1)

//  쿼터(Quarter, $0.25)의 개수, 다임(Dime, $0.10)의 개수, 니켈(Nickel, $0.05)의 개수, 페니(Penny, $0.01)

for (let i = 0; i < inputs.length; i++) {
  const payment = inputs[i]
  let querters = Number.parseInt(payment / 25)
  let dimes = Number.parseInt((payment % 25) / 10)
  let nickels = Number.parseInt(((payment % 25) % 10) / 5)
  let pennies = Number.parseInt(((payment % 25) % 10) % 5)
  console.log(querters, dimes, nickels, pennies)
}
