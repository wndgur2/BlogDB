// 최대 단어 개수 100_000 개
// 최대 단어 길이 10_000 글자
// 최대 키워드 개수 100_000 개
// 최대 키워드 길이 10_000 글자
function solution(words, queries) {
    const result = []
    
    // words에 대한 Trie 생성
    const postfix = new Trie()
    const prefix = new Trie()
    
    // Trie에 입력
    words.forEach((word)=>{
        postfix.add(word)
        prefix.add(reverseString(word))
    })
    
    // query 호출
    queries.forEach((query)=>{
        if(query[0]=='?') result.push(prefix.searchAmount(reverseString(query)))
        else result.push(postfix.searchAmount(query))
    })
    
    return result
    
    function reverseString(string){
        return Array.from(string).reverse().join("")
    }
}

class Node{
    constructor(key, value){
        this.key = key
        this.value = value
        this.children = new Map()
        this.countPerDepth = new Map()
    }
}

class Trie{
    constructor(){
        this.root = new Node("", "")
        this.root.countPerDepth.set(0, 1)
    }
    
    add(string){
        let node = this.root
        Array.from(string).forEach(c=>{
            // count
            const depthLeft = string.length - node.value.length
            if(!node.countPerDepth.has(depthLeft))
                node.countPerDepth.set(depthLeft, 1)
            else
                node.countPerDepth.set(depthLeft, node.countPerDepth.get(depthLeft)+1)
            
            if(!node.children.has(c)){
                node.children.set(c, new Node(c, node.value+c))
            }
            node = node.children.get(c)
        })
        node.countPerDepth.set(0, 1)
    }
    
    searchAmount(string){
        const word = string.replaceAll('?', '')
        const depthLeft = string.length - word.length
        
        let node = this.root
        for(let i=0; i<word.length; i++){
            if(!node.children.has(word[i])) return 0
            node = node.children.get(word[i])
        }
        if(node.countPerDepth.has(depthLeft))
            return node.countPerDepth.get(depthLeft)
        else return 0
    }
}