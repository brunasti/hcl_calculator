var calculatorApp = angular.module('calculatorApp',[]);


calculatorApp.service('dataService', function($http) {
    // delete $http.defaults.headers.common['X-Requested-With'];
    this.getData = function(n1, n2, operation) {
        console.log("GET "+n1+", "+n2+ ", "+operation)
        $http({
            method: 'GET',
            url: '/calculator/'+operation,
            params: 'a='+n1+' b='+n2
        }).success(function(data){
            console.log("DATA : "+data)
            return data
        }).error(function(){
            alert("error");
        });
    }
});

calculatorApp.controller('AngularJSCtrl', function($scope, dataService) {
    $scope.data = dataService.getData();
});

const calculate = (n1, operator, n2) => {
    const firstNum = parseFloat(n1)
    const secondNum = parseFloat(n2)
    if (operator === 'add') return add(firstNum, secondNum)
    if (operator === 'subtract') return firstNum - secondNum
    if (operator === 'multiply') return firstNum * secondNum
    if (operator === 'divide') return firstNum / secondNum
}

var result = -1

const add = (n1, n2) => {
    console.log("ADD "+n1+" and "+n2)
    // console.log("calculatorApp "+calculatorApp)

    request = new XMLHttpRequest();
    request.open('GET', '/calculator/add?a='+n1+'&b='+n2, false);  // `false` makes the request synchronous
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

keys.addEventListener('click', e => {
    if (!e.target.matches('button')) return
    const key = e.target
    const displayedNum = display.textContent
    const resultString = createResultString(key, displayedNum, calculator.dataset)

    display.textContent = resultString
    updateCalculatorState(key, calculator, resultString, displayedNum)
    updateVisualState(key, calculator)
})
