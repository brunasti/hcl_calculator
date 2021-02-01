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
    request = new XMLHttpRequest();
    request.open('GET', '/calculator/'+operation+'?a='+n1+'&b='+n2, false);  // `false` makes the request synchronous
    request.send(null);

    if (request.status === 200) {
        obj = JSON.parse(request.responseText);
        result = obj.result
    }

    return result
}

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
    const keyContent = key.textContent
    const keyType = getKeyType(key)
    const {
        firstValue,
        operator,
        modValue,
        previousKeyType
    } = state

    if (keyType === 'number') {
        return displayedNum === '0' ||
        previousKeyType === 'operator' ||
        previousKeyType === 'calculate'
            ? keyContent
            : displayedNum + keyContent
    }

    if (keyType === 'decimal') {
        if (!displayedNum.includes('.')) return displayedNum + '.'
        if (previousKeyType === 'operator' || previousKeyType === 'calculate') return '0.'
        return displayedNum
    }

    if (keyType === 'operator') {
        return firstValue &&
        operator &&
        previousKeyType !== 'operator' &&
        previousKeyType !== 'calculate'
            ? calculate(firstValue, operator, displayedNum)
            : displayedNum
    }

    if (keyType === 'clear') return 0

    if (keyType === 'calculate') {
        return firstValue
            ? previousKeyType === 'calculate'
                ? calculate(displayedNum, operator, modValue)
                : calculate(firstValue, operator, displayedNum)
            : displayedNum
    }

    if (keyType === 'history') {
        getHistory()
        return displayedNum
    }
}


const getHistory = () => {
    request = new XMLHttpRequest();
    request.open('GET', '/calculator/history', false);  // `false` makes the request synchronous
    request.send(null);

    if (request.status === 200) {
        obj = JSON.parse(request.responseText);
        result = "";
        for (i=0; i<obj.length; i++) {
            oper = obj[i];
            result = oper.operationType + " : "+oper.paramA+" and "+oper.paramB+" = "+oper.result + "\n"+result;
        }
        result = "History:\n"+result;
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
