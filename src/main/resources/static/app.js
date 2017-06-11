/**
 * Created by stas on 09/06/17.
 */

var MapGoogle = React.createClass({
    handleMap(){
        const moscow = {lat: 55.75222, lng: 37.61556};
        const map = new google.maps.Map(document.getElementById('map'), {
            zoom: 4,
            center: moscow
        });
        fetch('http://localhost:8181/loadAllAddresses')
            .then(response => {
                return response.json();
            })
            .then(arr => {
                arr.map(coordinate => {
                    let latlng = new google.maps.LatLng(coordinate.latitude, coordinate.longitude);
                    new google.maps.Marker({
                        position: latlng,
                        map: map
                    });
                })
            });
    },
    componentDidUpdate(updateMap){
        if (updateMap.needUpdate) {
            this.handleMap();
        }
    },
    componentDidMount() {
        this.handleMap();
    },
    render(){
        return (
            <div id="map">
            </div>
        )
    }

});


var Application = React.createClass({
    getInitialState(){
        return {
            updateMap: false
        }
    },
    handleSubmit(event){
        event.preventDefault();
        document.getElementById("address").style.borderColor = 'gray';
        fetch(`http://localhost:8181/saveAddress?address=${event.target.address.value}`, {
            method: 'POST'
        })
            .then( (response) => {
                return response.json()
            }).then( (body) => {
            this.setState({updateMap:body.successError});
            if (!body.successError) {
                document.getElementById("address").style.borderColor = 'red';
            }
        });
    },
    needUpdate(){
        this.setState({
            updateMap:this.state.updateMap
        });
    },
    render() {
        return (
            <div>
                <h3>My Google Maps Demo</h3>
                <form onSubmit={this.handleSubmit}>
                    <input type="text" id="address" name="address" className="focusedInput"
                           autoFocus="true" placeholder="&#61442; Search address..."/>
                    <input type="submit" className="addButton" value="Add"/>
                </form>
                <MapGoogle needUpdate={this.needUpdate}/>
            </div>
        )


    }
});

ReactDOM.render(
    <Application />,
    document.getElementById('root')
);

