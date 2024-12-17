using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;

namespace Rest.Models;

[Table("videos")]
public partial class Video
{
    [Column("id")]
    public int Id { get; set; }

    [Column("nombre")]
    public string Nombre { get; set; } = null!;

    [Column("contenido")]
    public string Contenido { get; set; } = null!;

    [Column("fecha_subida")]
    public DateTime? FechaSubida { get; set; }
}
