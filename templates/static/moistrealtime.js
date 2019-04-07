//JSONDATA
var moistURL = 'https://api.particle.io/v1/devices/350025000b47363339343638/moisturePercentage?access_token=1aa09ff2c1a069ca69c16e6822b0602412a8c1c4';
var tempURL = 'https://api.particle.io/v1/devices/350025000b47363339343638/temperature?access_token=1aa09ff2c1a069ca69c16e6822b0602412a8c1c4';
var humidURL = 'https://api.particle.io/v1/devices/350025000b47363339343638/humidity?access_token=1aa09ff2c1a069ca69c16e6822b0602412a8c1c4';
var globalmoist = {'percent' : null } ;
var globaltemp = {'percent' : null } ;
var globalhumid = {'percent' : null } ;
getjson(moistURL, globalmoist);
getjson(humidURL, globalhumid);
getjson(tempURL, globaltemp);

setInterval(getjson(moistURL, globalmoist), 3001);
setInterval(getjson(humidURL, globalhumid),3002);
setInterval(getjson(tempURL, globaltemp),3003);

function getjson(url, value){
  $.getJSON(url, function(data) {
    console.log(data);
    (value).percent = data.result;
    console.log(value)
    console.log(data.result);
  });
}
var speedCanvas = document.getElementById("speedChart");

Chart.defaults.global.defaultFontFamily = "Lato";
Chart.defaults.global.defaultFontSize = 18;
var cm = globalmoist.percent;
console.log('Current Value:');
console.log(cm);


var speedData = {
  labels: [-5, -4, -3, -2, -1, 0],
  datasets: [{
    label: "Moisture %",
    fill: false,
    data: [cm, cm, cm, cm, cm, cm, cm, cm, cm],
    lineTension: 0.5,
    backgroundColor: 'transparent',
    //backgroundColor:'rgba(0,0,255,0.5)',
    borderColor: 'blue',
    borderWidth: 3,
   
    usePointStyle: false,
    pointBorderColor: 'blue',
    pointBackgroundColor: 'blue',
    pointRadius: 3,
    pointHoverRadius: 10,
    pointHitRadius: 30,
    pointBorderWidth: 2,
    pointStyle: 'circle'
  }]
};

var chartOptions = {
  legend: {
    display: true,
    position: 'top',
    labels: {
      boxWidth: 30,
      fontColor: 'black'
    }
  },
  scales: {
    yAxes: [{
      display: true,
      gridLines: {
                color: "rgba(0, 0, 0, 0)",
            },
      ticks: {
        suggestedMin: cm,    // minimum will be 0, unless there is a lower value.
        // OR //
        suggestedMax: cm ,
        beginAtZero: false   // minimum value will be 0.
      }
    }],
    xAxes: [{
      display: true,
      gridLines: {
                color: "rgba(0, 0, 0, 0)",
            },
      ticks: {
        suggestedMin: 5,    // minimum will be 0, unless there is a lower value.
        min: -5,
        
        beginAtZero: false   // minimum value will be 0.
        
      }
    }]
  },
  
  title: {
    display:false,
    fontColor: 'black',
    text: "Current Dampness of the Soil",
    position: 'top'
  }
};
var cnt = 0;
setInterval(adddata,1000)
function adddata(){
  var newdata = Math.floor(Math.random()*(75-65)+65);
  lineChart.data.datasets[0].data.shift();
  lineChart.data.datasets[0].data.push(globalmoist.percent);
  lineChart.update();
  cnt++;
}


var lineChart = new Chart(speedCanvas, {
  type: 'line',
  data: speedData,
  options: chartOptions
});
