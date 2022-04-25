Write-Host -ForegroundColor DarkMagenta "Interrogation de Heroku CLI..."

$cmd = heroku pg:credentials:url --app "l3m-pi-serveur"

If (!$?) {
    Write-Host -ForegroundColor Red "Impossible de récupérer les informations !"
}
Else {
    $line = $cmd | Select-String -Pattern '"dbname=.* host=.* port=.* user=.* password=.* sslmode=.*"' |
    %{ $_.ToString().Trim('"'," ") }

    $tline = ($line -replace ' ',';')
    $tab = ($tline -split ';')
    $cred = @{}

    ForEach ($t in $tab) {
        $_t = ($t -split '=')
        $cred[$_t[0]] = $_t[1]
        Write-Host ($_t[0]+" -> "+$_t[1])
    }

    'JDBC_DATABASE_URL="jdbc:postgresql://'+$cred['host']+':'+$cred['port']+'/'+$cred['dbname']+'?sslmode='+$cred['sslmode']+'&user='+$cred['user']+'&password='+$cred['password']+'"' | Set-Content ".\.env"

    Write-Host -ForegroundColor Green "Mise a jour du fichier .env reussie !"
}
