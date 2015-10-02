var fs = require( 'fs' );
var http = require( 'http' );

var scribe = fs.createWriteStream("output.txt");



function getFormValuesFromURL( url )
{
    var kvs = {};
    var parts = url.split( "?" );
    var key_value_pairs = parts[1].split( "&" );
    for( var i = 0; i < key_value_pairs.length; i++ )
    {
        var key_value = key_value_pairs[i].split( "=" );
        kvs[ key_value[0] ] = key_value[1];
    }
    //console.log( kvs );
    return kvs
}




function server_fun( req, res )
{
    //console.log( req.url );
    // ...
    var filename = "./" + req.url;
    try {
        var contents = fs.readFileSync( filename ).toString();
        res.writeHead( 200 );
        res.end( contents );

       

    }
    catch( exp ) {
        // console.log( "huh?", req.url.indexOf( "second_form?" ) );
        if( req.url.indexOf( "first_form?" ) >= 0 )
        {
            var arr = getFormValuesFromURL( req.url );
            res.writeHead( 200 );
            res.end("you submitted the first form " + arr[ "how_many" ] );
            scribe.write(arr[ "how_many"] + "\n" + arr["mood"] + "\n"); //not logging color obviously
            
        }
        else if( req.url.indexOf( "second_form?" ) >= 0 )
        {
            var arrf = getFormValuesFromURL( req.url );
            res.writeHead( 200 );
            res.end( "You submitted the second form!!!!!" );
            scribe.write(arrf[ "animal"] + "\n" );

        }
        else if(req.url.indexOf("third_form?") >=0)
        {
            var arrg = getFormValuesFromURL(req.url);
            res.writeHead(200);
            res.end("You did the third form and you said " + arrg["raaadio"]);
            scribe.write(arrg["raaadio"] + "\n");

        }else if(req.url.indexOf("fourth_form?") >=0)
            {
                var arrb = getFormValuesFromURL(req.url);
                res.writeHead(200);
                res.end(" u are now in th club ");
                scribe.write(arrb["uname"] + "\n" + arrb["pword"] + "\n");
            }else {
            // console.log( exp );
            res.writeHead( 404 );
            res.end( "Cannot find file: " + filename );
        }
        
        
    }
}

var server = http.createServer( server_fun );

server.listen( 8080 );
