#r "paket:
version 5.241.2
framework: netstandard20
source https://api.nuget.org/v3/index.json
nuget Be.Vlaanderen.Basisregisters.Build.Pipeline 3.2.0
nuget Fake.IO.Zip 5.19.0  //"

open Fake.Core
open Fake.Core.TargetOperators
open Fake.IO
open Fake.IO.FileSystemOperators

let utf8 = System.Text.Encoding.UTF8
let destinationDirectory = "flood-packages"
let userFilesZip = destinationDirectory @@ "user-files.zip"
let userFiles = "user-files"

let createFloodPackage (file: System.IO.FileInfo) = 
    let scenario = System.IO.Path.GetFileNameWithoutExtension file.Name
    let scenarioDirectory = destinationDirectory @@ scenario
    Shell.mkdir scenarioDirectory
    Shell.copy scenarioDirectory [ userFilesZip; file.ToString() ]
    let floodSimulationFile = scenarioDirectory @@ "FloodSimumlation.scala"
    Shell.rename floodSimulationFile (scenarioDirectory @@ file.Name)
    Shell.regexReplaceInFileWithEncoding "class.+(extends.+Simulation)" "class FloodSimulation $1" utf8 floodSimulationFile

Target.create "Clean_flood_directory" (fun _ -> 
    DirectoryInfo.ensure (DirectoryInfo.ofPath destinationDirectory)
    Shell.cleanDir destinationDirectory
)

Target.create "Zip_user_files" (fun _ -> 
    // TODO: create the zip from user-files/**/*
    // https://fake.build/apidocs/v5/fake-io-zip.html
    Zip.zipSpec userFilesZip [] 
)

Target.create "Clean_up_temporary_files" (fun _ -> File.delete userFilesZip)

Target.create "Create_flood_upload_packages" (fun _ -> 
    let floodDirectory = DirectoryInfo.ofPath (userFiles @@ "simulations" @@ "flood")
    DirectoryInfo
        .getMatchingFiles "*.scala" floodDirectory
        |> Array.iter createFloodPackage
)

Target.create "Flood" ignore

"Clean_flood_directory" ==> "Zip_user_files" ==> "Create_flood_upload_packages" ==> "Clean_up_temporary_files" ==> "Flood"

Target.runOrDefault "Flood"