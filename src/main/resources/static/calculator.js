const calculate = (n1, operator, n2) => {
    const firstNum = parseFloat(n1)
    const secondNum = parseFloat(n2)
    if (operator === 'add') return operation(firstNum, secondNum, 'add')
    if (operator === 'subtract') return operation(firstNum, secondNum, 'subtract')
    if (operator === 'multiply') return operation(firstNum, secondNum, 'multiply')
    if (operator === 'divide') return operation(firstNum, secondNum, 'divide')
}

var result = -1

const operation = (n1, n2, operation) => {
    console.log(operation+" for "+n1+" and "+n2)
    // console.log("calculatorApp "+calculatorApp)

    request = new XMLHttpRequest();
    request.open('GET', '/calculator/'+operation+'?a='+n1+'&b='+n2, false);  // `false` makes the request synchronous
    request.send(null);

    if (request.status === 200) {
        console.log(request.responseText);
        obj = JSON.parse(request.responseText);
        console.log(obj);
        console.log(obj.result);
        result = obj.result
    }

    return result
}

// const add = (n1, n2) => {
//     console.log("ADD "+n1+" and "+n2)
//     // // console.log("calculatorApp "+calculatorApp)
//     //
//     // request = new XMLHttpRequest();
//     // request.open('GET', '/calculator/add?a='+n1+'&b='+n2, false);  // `false` makes the request synchronous
//     // request.send(null);
//     //
//     // if (request.status === 200) {
//     //     console.log(request.responseText);
//     //     obj = JSON.parse(request.responseText);
//     //     console.log(obj);
//     //     console.log(obj.result);
//     //     result = obj.result
//     // }
//     //
//     return operation(n1,n2,'add')
// }
//
// const subtract = (n1, n2) => {
//     return operation(n1,n2,'subtract')
// }
//


const getKeyType = key => {
    const { action } = key.dataset
    if (!action) return 'number'
    if (
        action === 'add' ||
        action === 'subtract' ||
        action === 'multiply' ||
        action === 'divide'
    ) return 'operator'
    // For everything else, return the action
    return action
}

const createResultString = (key, displayedNum, state) => {
    console.log("key "+key)
    const keyContent = key.textContent
    console.log("keyContent "+keyContent)
    const keyType = getKeyType(key)
    console.log("keyType "+keyType)
    const {
        firstValue,
        operator,
        modValue,
        previousKeyType
    } = state

    console.log("  - X1")

    if (keyType === 'number') {
        return displayedNum === '0' ||
        previousKeyType === 'operator' ||
        previousKeyType === 'calculate'
            ? keyContent
            : displayedNum + keyContent
    }

    console.log("  - X2")

    if (keyType === 'decimal') {
        if (!displayedNum.includes('.')) return displayedNum + '.'
        if (previousKeyType === 'operator' || previousKeyType === 'calculate') return '0.'
        return displayedNum
    }

    console.log("  - X3")

    if (keyType === 'operator') {
        return firstValue &&
        operator &&
        previousKeyType !== 'operator' &&
        previousKeyType !== 'calculate'
            ? calculate(firstValue, operator, displayedNum)
            : displayedNum
    }

    console.log("  - X4")

    if (keyType === 'clear') return 0

    console.log("  - X5")

    if (keyType === 'calculate') {
        return firstValue
            ? previousKeyType === 'calculate'
                ? calculate(displayedNum, operator, modValue)
                : calculate(firstValue, operator, displayedNum)
            : displayedNum
    }

    console.log("  - X9")

    if (keyType === 'history') {
        console.log("HISTORY.....")
        getHistory()
        console.log("HISTORY GOT !")
        return displayedNum
    }
}


const getHistory = () => {
    console.log("getting HISTORY.....")
    request = new XMLHttpRequest();
    request.open('GET', '/calculator/history', false);  // `false` makes the request synchronous
    request.send(null);

    if (request.status === 200) {
        console.log(request.responseText);
        obj = JSON.parse(request.responseText);
        console.log(obj);
        result = obj
    }

    history.textContent = result
    return result
}



const updateCalculatorState = (key, calculator, calculatedValue, displayedNum) => {
    const keyType = getKeyType(key)
    const {
        firstValue,
        operator,
        modValue,
        previousKeyType
    } = calculator.dataset

    calculator.dataset.previousKeyType = keyType

    if (keyType === 'operator') {
        calculator.dataset.operator = key.dataset.action
        calculator.dataset.firstValue = firstValue &&
        operator &&
        previousKeyType !== 'operator' &&
        previousKeyType !== 'calculate'
            ? calculatedValue
            : displayedNum
    }

    if (keyType === 'calculate') {
        calculator.dataset.modValue = firstValue && previousKeyType === 'calculate'
            ? modValue
            : displayedNum
    }

    if (keyType === 'clear' && key.textContent === 'AC') {
        calculator.dataset.firstValue = ''
        calculator.dataset.modValue = ''
        calculator.dataset.operator = ''
        calculator.dataset.previousKeyType = ''
    }
}

const updateVisualState = (key, calculator) => {
    const keyType = getKeyType(key)
    Array.from(key.parentNode.children).forEach(k => k.classList.remove('is-depressed'))

    if (keyType === 'operator') key.classList.add('is-depressed')
    if (keyType === 'clear' && key.textContent !== 'AC') key.textContent = 'AC'
    if (keyType !== 'clear') {
        const clearButton = calculator.querySelector('[data-action=clear]')
        clearButton.textContent = 'CE'
    }
}

const calculator = document.querySelector('.calculator')
const display = calculator.querySelector('.calculator__display')
const keys = calculator.querySelector('.calculator__keys')
const history = calculator.querySelector('.calculator__history')

keys.addEventListener('click', e => {
    if (!e.target.matches('button')) return
    const key = e.target
    const displayedNum = display.textContent
    const resultString = createResultString(key, displayedNum, calculator.dataset)

    display.textContent = resultString
    updateCalculatorState(key, calculator, resultString, displayedNum)
    updateVisualState(key, calculator)
})
