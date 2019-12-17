#r "paket:
version 5.241.2
framework: netstandard20
source https://api.nuget.org/v3/index.json
nuget Be.Vlaanderen.Basisregisters.Build.Pipeline 3.2.0 //"

open Fake.Core
open Fake.Core.TargetOperators

Target.create "Clean_flood_directory" (fun _ -> printf "ToDo: clean te flood directory `rm -rf flood-packages`")
Target.create "Zip_user_files" (fun _ -> printf "ToDo: zip the user-files directory into flood-packages directory")
Target.create "Create_flood_upload_packages" (fun _ -> printf "ToDo: for each file in simulations/flood create a package with 'user-files.zip, floodsimulation.scala'")

Target.create "Flood" ignore

"Clean_flood_directory" ==> "Zip_user_files" ==> "Create_flood_upload_packages" ==> "Flood"

Target.runOrDefault "Flood"