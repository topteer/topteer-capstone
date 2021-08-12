function getRandomQuote(){
    var quotesArray = ["Never trust anyone who doesn’t drink coffee. – AJ Lee","Coffee is a kind of magic you can drink. ― Catherynne M. Valente","Never underestimate the power of a good cup of coffee. – Ursula Vernon","At least there was coffee. Reliable, delicious, life-giving coffee. – Mary H.K. Choi"]
    return quotesArray[Math.floor(Math.random() * quotesArray.length - 1) + 1];
}

var randomQuote = document.getElementById("quote")

randomQuote.innerHTML = getRandomQuote()