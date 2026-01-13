class Dequeue {
  head = null
  tail = null
  sum = 0
  length = 0

  pop() {
    if (this.size() === 0) return null

    const res = this.head

    this.head = res.next
    if (this.head) {
      this.head.prev = null
    } else {
      this.tail = null
    }

    this.sum -= res.value
    this.length--

    return res
  }

  push(node) {
    if (this.size() === 0) {
      this.head = node
      this.tail = node
    } else {
      this.tail.next = node
      node.prev = this.tail
      this.tail = node
    }
    this.sum += node.value
    this.length++
  }
  size() {
    let size = 0
    let node = this.head
    while (node) {
      size++
      node = node.next
    }
    return size
  }
}

class Node {
  prev = null
  next = null
  constructor(v) {
    this.value = v
  }
}

// example

const [queue1, queue2] = [
  [1, 2, 1, 2],
  [1, 10, 1, 2],
]
const N = queue1.length
var answer = -1
const deq1 = new Dequeue()
const deq2 = new Dequeue()

for (let i = 0; i < N; i++) {
  deq1.push(new Node(queue1[i]))
  deq2.push(new Node(queue2[i]))
}

console.log(deq1)
console.log(deq2)

let r = 0
while (deq1.sum !== deq2.sum) {
  if (deq1.sum > deq2.sum) {
    const n = deq1.pop()
    deq2.push(new Node(n.value))
  } else {
    const n = deq2.pop()
    deq1.push(new Node(n.value))
  }
  console.log(deq1)
  console.log(deq2)

  if (r++ == N * 2) break
}
if (deq1.sum === deq2.sum) answer = r

console.log(answer)
